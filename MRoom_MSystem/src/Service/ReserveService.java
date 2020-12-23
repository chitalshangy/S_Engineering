package Service;

import Dao.ConferenceDAO;
import Dao.IConferenceDAO;
import Dao.IReserveDAO;
import Dao.ReserveDAO;
import Po.Conference;
import Po.Reserve;
import Po.Room;
import Po.User;
import com.opensymphony.xwork2.ActionContext;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public class ReserveService implements IReserveService {
    private IReserveDAO reserveDAO;
    private IConferenceDAO conferenceDAO;
    private Map<String, Object> session,request;

    public void setConferenceDAO(IConferenceDAO conferenceDAO) {
        this.conferenceDAO = conferenceDAO;
    }

    public void setReserveDAO(IReserveDAO reserveDAO) {
        this.reserveDAO = reserveDAO;
    }

    public List InfoList(int page,int limit){
        return reserveDAO.findAll(page,limit);
    }

    public long Count(){
        return reserveDAO.infoCount();
    }

    public List InfoMyList(int page,int limit){
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        User user=(User) session.get("user");
        String uid=user.getUid();
        return reserveDAO.findMyAll(page,limit,uid);
    }

    public long CountMy(){
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        User user=(User) session.get("user");
        String uid=user.getUid();
        return reserveDAO.infoMyCount(uid);
    }


    public boolean addReserve(Reserve reserve){
        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        request=(Map) ctx.get("request");
        User user=(User) session.get("user");
        String uid=user.getUid();
        String rid=reserve.getRid();
        Date date=reserve.getDate();
        Time starttime=reserve.getStartTime();
        reserve.setReid(uid+rid+date+starttime);
        reserve.setUser(user);
        reserve.setState("1");
        Room r=new Room(rid);
        reserve.setRoom(r);
        Conference conference=new Conference(user,reserve);
        conference.setCidentity("预约者");
        //判断当前reserve是否可以被插入

        reserveDAO.save(reserve);
        conferenceDAO.addConference(conference);
        request.put("reid",uid+rid+date+starttime);
        return true;
    }

    public void deleteReserve(String reid){
        reserveDAO.deleteReserve(reid);

    }


}
