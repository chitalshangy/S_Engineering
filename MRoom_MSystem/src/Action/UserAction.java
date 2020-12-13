package Action;

import Po.Admin;
import Po.User;
import Service.IUserService;
import Service.UserService;

public class UserAction {
    private User user;
    private Admin admin = new Admin();
    private IUserService userService;
    public User getUser() {
        return user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String login(){
        if(user.getUid().length()==4){
            admin.setAid(user.getUid());
            admin.setApassword(user.getUpassword());
            if(userService.alogin(admin))return "admin_success";
        }
        else{
            if(userService.ulogin(user))return "other_success";
        }
        return "fail";
    }
}
