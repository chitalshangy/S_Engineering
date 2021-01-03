package Service;

import Dao.AdminDAO;
import Dao.RoomDAO;
import Dao.UserDAO;
import Po.Admin;
import Po.User;
import com.opensymphony.xwork2.ActionContext;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService {
    private UserDAO userDAO;
    private AdminDAO adminDAO;
    private RoomDAO roomDAO;

    private Map<String, Object> session;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    //普通用户登录
    public boolean ulogin(User user) {
        String id = user.getUid();
        String password = user.getUpassword();
        String hql = "from User as user where uid='" + id + "' and upassword='" + password + "'";

        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        List list = userDAO.findByhql(hql);
        if (list.size() == 1) {
            session.put("user", user);
            return true;
        }
        return false;
    }

    //管理员登录
    public boolean alogin(Admin admin) {
        String id = admin.getAid();
        String password = admin.getApassword();
        String hql = "from Admin as admin where aid='" + id + "' and apassword='" + password + "'";
        /*错误
        UserDAO userDAO=new UserDAO();
        */
        List list = adminDAO.findByhql(hql);
        if (list.size() == 1) {
            Map request;
            ActionContext ctx=ActionContext.getContext();
            request=(Map)ctx.get("request");
            request.put("id",id);
            request.put("password",password);
            return true;
        }
        return false;
    }

    //获得用户所有信息
    public List userList(int page, int limit) {
        return userDAO.findAll(page, limit);
    }

    //获得会议室所有信息
    public List roomList(int page, int limit){
        return roomDAO.findAll(page,limit);
    }

    //获取User总行数
    public long userCount() {
        return userDAO.userCount();
    }

    //获取Room总行数
    public long roomCount(){
        return roomDAO.roomCount();
    }

    public void delUser(String uid) {
        userDAO.del(uid);
    }

    public void updateUser(String uid, String uname, String upassword, String uphone) {
        userDAO.update(uid, uname, upassword, uphone);
    }

    public void updateAdmin(String aid, String apassword, String aphone){
        adminDAO.update(aid,apassword,aphone);
    }
}
