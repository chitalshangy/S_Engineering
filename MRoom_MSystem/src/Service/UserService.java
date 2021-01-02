package Service;

import Dao.AdminDAO;
import Dao.UserDAO;
import Po.Admin;
import Po.User;
import com.opensymphony.xwork2.ActionContext;

import java.util.List;
import java.util.Map;

public class UserService implements IUserService {
    private UserDAO userDAO;
    private AdminDAO adminDAO;
    private Map<String, Object> session;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    //普通用户登录
    public boolean ulogin(User user) {
        String id = user.getUid();
        String password = user.getUpassword();
        String hql = "from User as user where uid='" + id + "' and upassword='" + password + "'";

        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        List list = userDAO.findByhql(hql);
        if (list != null) {
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
        if (list.size() == 1) return true;
        return false;
    }

    //获得所有信息
    public List InfoList(int page, int limit) {
        return userDAO.findAll(page, limit);
    }

    //获取总行数
    public long Count() {
        return userDAO.infoCount();
    }

    public void delUser(String uid) {
        userDAO.del(uid);
    }

    public void updateUser(String uid, String uname, String upassword, String uidentity) {
        userDAO.update(uid, uname, upassword, uidentity);
    }
}
