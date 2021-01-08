package Service;


import Dao.IConferenceDAO;
import Dao.IReserveDAO;
import Po.Conference;
import Po.Reserve;
import Po.Room;
import Po.User;
import com.opensymphony.xwork2.ActionContext;

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
    private Map<String, Object> session, request;

    public void setConferenceDAO(IConferenceDAO conferenceDAO) {
        this.conferenceDAO = conferenceDAO;
    }

    public void setReserveDAO(IReserveDAO reserveDAO) {
        this.reserveDAO = reserveDAO;
    }

    public List InfoList(int page, int limit) {
        return reserveDAO.findAll(page, limit);
    }

    public long Count() {
        return reserveDAO.infoCount();
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
            request.put("tip", "您的与会人数超过最大会议室容纳人数");
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
                request.put("reid", uid + rid + date + start);
                return true;
            }
        }
        request.put("tip", "目前无空闲会议室");
        return false;
    }

    public boolean addReserve(Reserve reserve) {
        ActionContext ctx = ActionContext.getContext();
        request = (Map) ctx.get("request");
        if (!reserveDAO.judge(reserve.getRid(), reserve.getDate(), reserve.getStartTime(), reserve.getEndTime())) {
            request.put("tip", "您预约的时段与已有预约冲突，请重新预约");
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
        reserveDAO.deleteReserve(reid);
        conferenceDAO.deleteConference(reid);
    }


}
