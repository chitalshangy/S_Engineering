package Action;

import Po.User;
import Service.UserService;
import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

public class UserAction {

    private Map session;

    private User user;
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }
    public String login(){
        ActionContext ctx= ActionContext.getContext();
        session =(Map) ctx.getSession();
        UserService userService=new UserService();
        if(userService.login(user)){
            if(user.getUser_id().length()==4) {
                session.put("user", user.getUser_id());
                return "admin_success";
            }
            else{
                session.put("user",user.getUser_id());
                return "other_success";
            }
        }
        return "fail";
    }
}
