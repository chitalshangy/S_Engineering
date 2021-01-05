package Service;

import Dao.IConferenceDAO;
import Po.Conference;
import Po.Reserve;
import Po.User;
import com.opensymphony.xwork2.ActionContext;
import extend.faceEngineTest.webcam;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class ConferenceServiceImpl implements IConferenceService {
    private IConferenceDAO conferenceDAO = null;
    private Map<String, Object> session, request;

    public void setConferenceDAO(IConferenceDAO conferenceDAO) {
        this.conferenceDAO = conferenceDAO;
    }

    public void deleteConference(String reid) {
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        User user = (User) session.get("user");
        Reserve reserve = new Reserve(reid);
        Conference conference = new Conference(user, reserve);
        conferenceDAO.deleteConference(conference);
    }

    public void deleteConferenceByManager(String uid) {
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        String reid = (String) session.get("reid");
        User user = new User(uid);
        Reserve reserve = new Reserve(reid);
        Conference conference = new Conference(user, reserve);
        conferenceDAO.deleteConference(conference);
    }

    public void addConference(Conference conference) {
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        request = (Map) ctx.get("request");
        User user = (User) session.get("user");
        conference.setCidentity("参与者");
        conference.setUser(user);

        String hql = "from Reserve r where r.reid='" + conference.getReserve().getReid() + "' and r.open='on'";
        List list = conferenceDAO.findByhql(hql);
        if (list.isEmpty()) {
            request.put("tip", "插入会议记录失败（该会议不开放加入或您的预约流水号输入错误）");
        } else {
            conferenceDAO.addConference(conference);
            request.put("tip", "插入会议记录成功");
        }
    }

    public List InfoList(int page, int limit) {
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        User user = (User) session.get("user");
        String uid = user.getUid();
        return conferenceDAO.findAll(page, limit, uid);
    }

    public long Count() {
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        User user = (User) session.get("user");
        String uid = user.getUid();
        return conferenceDAO.infoCount(uid);
    }

    public List InfoListPa(String reid, int page, int limit) {
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        session.put("reid", reid);
        return conferenceDAO.findAllPo(reid, page, limit);
    }

    public long CountPa(String reid) {
        return conferenceDAO.infoCountPo(reid);
    }

    public Boolean checkIn(String a, String rid) throws IOException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] b = decoder.decode(a);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        OutputStream out1 = new FileOutputStream("D:\\testpic.jpg");
        out1.write(b);
        out1.flush();
        out1.close();
        webcam c = new webcam();

        Long current_time = System.currentTimeMillis();
        Time ten_less_time = new Time(current_time - 10 * 1000 * 60);
        Time ten_more_time = new Time(current_time + 10 * 1000 * 60);
        Time check_curr_time = new Time(current_time);

        java.util.Date now = new java.util.Date();
        // java.util.Date -> java.time.LocalDate
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // java.time.LocalDate -> java.sql.Date
        Date date = java.sql.Date.valueOf(localDate);

        /*
                String hql = "select c.user.uid from Conference c,Reserve r where r.reid = c.reserve.reid " +
                "and r.room.rid = '"+rid+"' and r.date = '"+date+"' and '"+
                ten_less_time + "' <= r.startTime and '" +
                ten_more_time + "' >= r.startTime";
        */

        String hql1 = "select r.reid from Reserve r where r.room.rid = '" + rid + "' and r.date = '" + date + "' and '" +
                ten_less_time + "' <= r.startTime and '" +
                ten_more_time + "' >= r.startTime";
        List reidList = conferenceDAO.findByhql(hql1);
        String reid = (String) reidList.get(0);

        String hql2 = "select c.user.uid from Conference c,Reserve r where c.reserve.reid=r.reid and r.reid ='" + reid + "'";
        List conlist = conferenceDAO.findByhql(hql2);

        for (int i = 0; i < conlist.size(); ++i) {
            System.out.println((String) conlist.get(i));
        }

        for (int i = 0; i < conlist.size(); ++i) {
            String uid = (String) conlist.get(i);
            String hql3 = "select u.upicture from User u where u.uid='" + uid + "'";
            List list = conferenceDAO.findByhql(hql3);

            //获取数据库内照片路径
            String tmp ="C:\\Users\\Chital\\Documents\\GitHub\\S_Engineering\\MRoom_MSystem\\web\\"+list.get(0);
            if (c.fun(tmp) > 0.7) {
                System.out.println(uid);
                System.out.println("succcess");
                conferenceDAO.checkInConference(reid, uid, check_curr_time);
                break;
            } else {
                System.out.println("fail");
            }
        }
        return true;
    }
}