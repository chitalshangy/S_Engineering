package Service;

import Dao.AdminDAO;
import Dao.IAdminDAO;
import Dao.IUserDAO;
import Dao.UserDAO;
import Po.Admin;
import Po.User;

import java.util.List;

public class UserService implements IUserService{
    private IUserDAO userDAO;
    private IAdminDAO adminDAO;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setAdminDAO(IAdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    //普通用户登录
    public boolean ulogin(User user){
        String id=user.getUid();
        String password=user.getUpassword();
        String hql="from User as user where uid='" +id+ "' and upassword='" + password +"'";
        /*错误
        UserDAO userDAO=new UserDAO();
        */
        List list=userDAO.findByhql(hql);
        if(list!=null)return true;
        return false;
    }
    //管理员登录
    public boolean alogin(Admin admin){
        String id=admin.getAid();
        String password=admin.getApassword();
        String hql="from Admin as admin where aid='" +id+ "' and apassword='" + password +"'";
        /*错误
        UserDAO userDAO=new UserDAO();
        */
        List list=adminDAO.findByhql(hql);
        if(list!=null)return true;
        return false;
    }
}
