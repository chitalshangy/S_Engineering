package Action;

import Po.Admin;
import Po.User;
import Service.IUserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String login() {
        if (user.getUid().length() == 4) {
            admin.setAid(user.getUid());
            admin.setApassword(user.getUpassword());
            if (userService.alogin(admin)) {
                return "admin_success";
            }
        } else if (user.getUid().length() == 6) {
            if (userService.ulogin(user)) return "other_success";
        }
        return "fail";
    }

    public String delete() {
        HttpServletRequest reqeust = ServletActionContext.getRequest();
        userService.delUser(reqeust.getParameter("uid"));
        return "success";
    }

    public String Userupdate() {
        HttpServletRequest reqeust = ServletActionContext.getRequest();
        String uid = reqeust.getParameter("uid");
        String uname = reqeust.getParameter("uname");
        String upassword = reqeust.getParameter("upassword");
        String uphone = reqeust.getParameter("uphone");
        userService.updateUser(uid, uname, upassword, uphone);
        return "success";
    }

    public String Adminupdate() {
        HttpServletRequest reqeust = ServletActionContext.getRequest();
        String aid = reqeust.getParameter("aid");
        String apassword = reqeust.getParameter("apassword");
        String aphone = reqeust.getParameter("aphone");
        userService.updateAdmin(aid, apassword, aphone);
        return "success";
    }


    @ResponseBody
    @RequestMapping("/uploadUser")
    public Map<String, String> uploadUser() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart(request);

        MultipartFile file = multipartRequest.getFile("file");
        Map<String, String> map = new HashMap<>();
        userService.uploadUser(file);
        map.put("msg", "ok");
        return map;
    }
}
