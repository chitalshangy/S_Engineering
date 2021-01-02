package Service;

import Po.Admin;
import Po.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public void updateUser(String uid, String uname, String upassword, String uidentity);

    public void delUser(String uid);

    public void updateAdmin(String aid, String apassword, String aphone);
}
