package Service;

import Po.Admin;
import Po.User;

import java.util.List;

public interface IUserService {
    //普通用户登录
    public boolean ulogin(User user);

    //管理员登录
    public boolean alogin(Admin admin);

    //获得所有信息
    public List InfoList(int page, int limit);

    //获取总行数
    public long Count();

    public void updateUser(String uid, String uname, String upassword, String uidentity);

    public void delUser(String uid);

    public void updateAdmin(String aid, String apassword, String aphone);
}
