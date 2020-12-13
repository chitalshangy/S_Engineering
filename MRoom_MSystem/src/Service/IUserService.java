package Service;

import Po.Admin;
import Po.User;

import java.util.List;

public interface IUserService {
    //普通用户登录
    public boolean ulogin(User user);
    //管理员登录
    public boolean alogin(Admin admin);
}
