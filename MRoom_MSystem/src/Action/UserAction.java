package Action;

import Po.User;
import Service.UserService;

public class UserAction {
    private User user;
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }
    public String login(){
        UserService userService=new UserService();
        if(userService.login(user)){
            return "success";
        }
        return "fail";
    }
}
