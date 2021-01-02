package Service;

import Dao.ConferenceDAO;
import Dao.IConferenceDAO;
import Po.Conference;
import Po.Reserve;
import Po.User;
import com.opensymphony.xwork2.ActionContext;

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
}
