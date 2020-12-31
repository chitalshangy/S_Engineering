package Service;

import Dao.AdminDAO;
import Dao.RoomDAO;
import Dao.UserDAO;
import Po.Admin;
import Po.User;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import upLoad.ExcelUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService {
    private UserDAO userDAO;
    private AdminDAO adminDAO;
    private RoomDAO roomDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO=roomDAO;
    }


    //普通用户登录
    public boolean ulogin(User user) {
        String id = user.getUid();
        String password = user.getUpassword();
        String hql = "from User as user where uid='" + id + "' and upassword='" + password + "'";
        /*错误
        UserDAO userDAO=new UserDAO();
        */
        List list = userDAO.findByhql(hql);
        if (list != null) return true;
        return false;
    }

    //管理员登录
    public boolean alogin(Admin admin) {
        String id = admin.getAid();
        String password = admin.getApassword();
        String hql = "from Admin as admin where aid='" + id + "' and apassword='" + password + "'";
        /*错误
        UserDAO userDAO=new UserDAO();
        */
        List list = adminDAO.findByhql(hql);
        if (list.size() == 1){
            Map request;
            ActionContext ctx=ActionContext.getContext();
            request=(Map)ctx.get("request");
            request.put("id",id);
            request.put("password",password);
            return true;
        }
        return false;
    }

    //获得用户所有信息
    public List userList(int page, int limit) {
        return userDAO.findAll(page, limit);
    }

    //获得会议室所有信息
    public List roomList(int page, int limit){
        return roomDAO.findAll(page,limit);
    }

    //获取User总行数
    public long userCount() {
        return userDAO.userCount();
    }

    //获取Room总行数
    public long roomCount(){
        return roomDAO.roomCount();
    }

    public void delUser(String uid) {
        userDAO.del(uid);
    }

    public void updateUser(String uid, String uname, String upassword, String uphone) {
        userDAO.update(uid, uname, upassword, uphone);
    }

    public void updateAdmin(String aid, String apassword, String aphone){
        adminDAO.update(aid,apassword,aphone);
    }

    public String uploadUser(MultipartFile file){
        if(file.isEmpty()){
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<Object>> listob = null;
        try {
            listob = new ExcelUtils().getBankListByExcel(in,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            User user=new User();
            String hql="from User as user where uid='" + Integer.valueOf(String.valueOf(lo.get(0)));
            List list=userDAO.findByhql(hql);
            if(list==null) {
                continue;
            }
            user.setUid(String.valueOf(lo.get(0)));
            user.setUname(String.valueOf(lo.get(1)));
            user.setUpassword(String.valueOf(lo.get(2)));
            user.setUphone(String.valueOf(lo.get(3)));
            user.setUpicture(String.valueOf(lo.get(4)));

            userDAO.add(user);
        }
        return "success";
    }
}
