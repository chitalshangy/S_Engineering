package Action;

import Po.Admin;
import Po.User;
import Service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;


import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class UserAction extends ActionSupport {
    private User user;
    private Admin admin = new Admin();
    private IUserService userService;

    private File userExcel;
    private String userExcelContentType;
    private String userExcelFileName;

    public File getUserExcel() {
        return userExcel;
    }

    public void setUserExcel(File userExcel) {
        this.userExcel = userExcel;
    }

    public String getUserExcelContentType() {
        return userExcelContentType;
    }

    public void setUserExcelContentType(String userExcelContentType) {
        this.userExcelContentType = userExcelContentType;
    }

    public String getUserExcelFileName() {
        return userExcelFileName;
    }

    public void setUserExcelFileName(String userExcelFileName) {
        this.userExcelFileName = userExcelFileName;
    }

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
        HttpServletRequest request = ServletActionContext.getRequest();
        userService.delUser(request.getParameter("uid"));
        return "success";
    }

    public String Userupdate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        String upassword = request.getParameter("upassword");
        String uphone = request.getParameter("uphone");
        userService.updateUser(uid, uname, upassword, uphone);
        return "success";
    }

    public String Adminupdate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String aid = request.getParameter("aid");
        String apassword = request.getParameter("apassword");
        String aphone = request.getParameter("aphone");
        userService.updateAdmin(aid, apassword, aphone);
        return "success";
    }

    // 执行上传功能
    public String uploadFile() {
        if (!(userExcel == null)) {
            if (userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
                userService.importExcel(userExcel, userExcelFileName);
            }
        }
        return "list";
    }

    public void updateRstate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        userService.updateRstate(request.getParameter("rid"), Integer.valueOf(request.getParameter("rnum")), request.getParameter("rstate"), request.getParameter("raddress"));
    }
}