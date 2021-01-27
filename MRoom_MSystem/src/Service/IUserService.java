package Service;

import Po.Admin;
import Po.User;

import java.io.File;
import java.util.List;

public interface IUserService {

    //普通用户登录
    public boolean ulogin(User user);

    //管理员登录
    public boolean alogin(Admin admin);

    //获得用户所有信息
    public List userList(int page, int limit);

    //获取会议室所有信息
    public List roomList(int page, int limit);

    //获取User总行数
    public long userCount();

    //获取Room总行数
    public long roomCount();

    //更新用户信息
    public void updateUser(String uid, String uname, String upassword, String uidentity);

    //管理员删除用户
    public void delUser(String uid);

    //管理员更新本人信息
    public void updateAdmin(String aid, String apassword, String aphone);

    public void importExcel(File userExcel, String userExcelFileName);

    public void updateRstate(String rid, int rnum, String rstate, String raddress);
}
