package Service;


import Dao.IConferenceDAO;
import Dao.IInfomationDAO;
import Dao.IReserveDAO;
import Dao.InfomationDAO;
import Po.Conference;
import Po.Reserve;
import Po.Room;
import Po.User;
import com.opensymphony.xwork2.ActionContext;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

public class ReserveServiceImpl implements IReserveService {
    private IReserveDAO reserveDAO;
    private IConferenceDAO conferenceDAO;
    private IInfomationDAO infomationDAO;
    private Map<String, Object> session, request;

    public void setConferenceDAO(IConferenceDAO conferenceDAO) {
        this.conferenceDAO = conferenceDAO;
    }

    public void setReserveDAO(IReserveDAO reserveDAO) {
        this.reserveDAO = reserveDAO;
    }

    public void setInfomationDAO(IInfomationDAO infomationDAO) {
        this.infomationDAO = infomationDAO;
    }

    public List InfoList(int page, int limit) {
        return reserveDAO.findAll(page, limit);
    }

    public long Count() {
        return reserveDAO.infoCount();
    }


    public List history(int page, int limit) {
        return reserveDAO.history(page, limit);
    }

    public long historyCount() {
        return reserveDAO.historyCount();
    }

    public List InfoMyList(int page, int limit) {
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        User user = (User) session.get("user");
        String uid = user.getUid();
        return reserveDAO.findMyAll(page, limit, uid);
    }

    public long CountMy() {
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        User user = (User) session.get("user");
        String uid = user.getUid();
        return reserveDAO.infoMyCount(uid);
    }

    public boolean emergencyReserve(int num, Reserve reserve) {
        ActionContext ctx = ActionContext.getContext();
        request = (Map) ctx.get("request");
        String hql = "from Room r where r.rnum>=" + num + " order by r.rnum asc";
        List list = reserveDAO.findByhql(hql);
        if (list.isEmpty()) {
            request.put("tip", "您的与会人数超过最大会议室容纳人数或当前无可用会议室");
            return false;
        }
        java.util.Date now = new java.util.Date();
        // java.util.Date -> java.time.LocalDate
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // java.time.LocalDate -> java.sql.Date
        Date date = java.sql.Date.valueOf(localDate);

        Long time = System.currentTimeMillis();
        Time start = new Time(time);
        time += 30 * 1000 * 60;
        Time end = new Time(time);

        reserve.setDate(date);
        reserve.setStartTime(start);
        reserve.setEndTime(end);
        session = (Map) ctx.getSession();
        User user = (User) session.get("user");
        String uid = user.getUid();
        reserve.setUser(user);
        reserve.setState("1");
        reserve.setTitle("紧急会议");
        for (int i = 0; i < list.size(); ++i) {
            Room r = (Room) list.get(i);
            String rid = r.getRid();
            reserve.setRoom(r);
            if (!reserveDAO.judge(rid, date, reserve.getStartTime(), reserve.getEndTime())) continue;
            else {
                reserve.setReid(uid + rid + date + start);
                reserveDAO.save(reserve);
                Conference conference = new Conference(user, reserve);
                conference.setCidentity("预约者");
                conferenceDAO.addConference(conference);
                request.put("reid", uid + rid + date + start);
                return true;
            }
        }
        request.put("tip", "目前无空闲会议室或当前无可用会议室");
        return false;
    }

    public boolean addReserve(Reserve reserve) {
        ActionContext ctx = ActionContext.getContext();
        request = (Map) ctx.get("request");
        if (!reserveDAO.judge(reserve.getRid(), reserve.getDate(), reserve.getStartTime(), reserve.getEndTime())) {
            request.put("tip", "您预约的时段与已有预约冲突或当前无可用会议室，请重新预约");
            return false;
        }
        session = (Map) ctx.getSession();
        User user = (User) session.get("user");
        String uid = user.getUid();
        String rid = reserve.getRid();
        Date date = reserve.getDate();
        Time starttime = reserve.getStartTime();
        reserve.setReid(uid + rid + date + starttime);
        reserve.setUser(user);
        reserve.setState("1");

        String hql = "from Room r where r.rid='" + rid + "'";
        List list = reserveDAO.findByhql(hql);
        if (list.size() == 0) {
            request.put("tip", "预约会议室不存在，请重新预约");
            return false;
        }

        Room r = new Room(rid);
        reserve.setRoom(r);
        Conference conference = new Conference(user, reserve);
        conference.setCidentity("预约者");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        reserveDAO.save(reserve);
        conferenceDAO.addConference(conference);
        request.put("reid", uid + rid + date + starttime);
        return true;
    }

    public void deleteReserve(String reid) {
        List uidlist = conferenceDAO.findByhql("select c.user.uid from Conference c where c.reserve.reid='" + reid + "'");

        for(int i = 0; i < uidlist.size(); ++i){
            //发送通知
            Constants.useOfficial();
            Sender sender = new Sender("wyQaepXTnw5hhglTd6J1jg==");
            String messagePayload = "您的会议有新动态，请查看";
            String title = "踢出会议通知";
            String description = "您已被该会议踢出："+reid;
            String useraccount = (String) uidlist.get(i);    //useraccount非空白, 不能包含逗号, 长度小于128
            Message message = new Message.Builder()
                    .title(title)
                    .description(description).payload(messagePayload)
                    .restrictedPackageName("com.xiaomi.huiyi")
                    .passThrough(0)  //消息使用通知栏方式

                    .notifyType(2)     // 使用默认提示音提示
                    .build();
            try {
                sender.sendToUserAccount(message, useraccount, 3);
            } catch (IOException | ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } //根据useraccount, 发送消息到指定设备上
            infomationDAO.insert(useraccount,description,"未读");
        }

        reserveDAO.deleteReserve(reid);
        conferenceDAO.deleteConference(reid);
    }

    public void updateReserve(String reid){
        reserveDAO.updateReserve(reid);
    }

    public List findAll(String hql){
        return reserveDAO.findByhql(hql);
    }
}