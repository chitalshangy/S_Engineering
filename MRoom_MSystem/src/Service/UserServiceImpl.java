package Service;

import Dao.AdminDAO;
import Dao.RoomDAO;
import Dao.UserDAO;
import Po.Admin;
import Po.User;
import com.opensymphony.xwork2.ActionContext;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService {
    private UserDAO userDAO;
    private AdminDAO adminDAO;
    private RoomDAO roomDAO;

    private Map<String, Object> session;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    //普通用户登录
    public boolean ulogin(User user) {
        String id = user.getUid();
        String password = user.getUpassword();
        String hql = "from User as user where uid='" + id + "' and upassword='" + password + "'";

        ActionContext ctx = ActionContext.getContext();
        session = (Map) ctx.getSession();
        List list = userDAO.findByhql(hql);
        if (list.size() == 1) {
            session.put("user", user);
            return true;
        }
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
        if (list.size() == 1) {
            Map request;
            ActionContext ctx = ActionContext.getContext();
            request = (Map) ctx.get("request");
            request.put("id", id);
            request.put("password", password);
            return true;
        }
        return false;
    }

    //获得用户所有信息
    public List userList(int page, int limit) {
        return userDAO.findAll(page, limit);
    }

    //获得会议室所有信息
    public List roomList(int page, int limit) {
        return roomDAO.findAll(page, limit);
    }

    //获取User总行数
    public long userCount() {
        return userDAO.userCount();
    }

    //获取Room总行数
    public long roomCount() {
        return roomDAO.roomCount();
    }

    public void delUser(String uid) {
        userDAO.del(uid);
    }

    public void updateUser(String uid, String uname, String upassword, String uphone) {
        userDAO.update(uid, uname, upassword, uphone);
    }

    public void updateAdmin(String aid, String apassword, String aphone) {
        adminDAO.update(aid, apassword, aphone);
    }

    public void importExcel(File userExcel, String userExcelFileName){
        try {
            FileInputStream fileInputStream = new FileInputStream(userExcel);
            boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
            Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
            //2、读取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //3、读取行
            if(sheet.getPhysicalNumberOfRows() > 2){
                User user = null;
                for(int k = 1; k < sheet.getPhysicalNumberOfRows(); ++k){

                    Row row = sheet.getRow(k);
                    user = new User();

                    Cell cell0 = row.getCell(0);
                    if(cell0!=null){
                        cell0.setCellType(CellType.STRING);
                        user.setUid(cell0.getStringCellValue());
                    }

                    Cell cell1 = row.getCell(1);
                    if(cell1!=null){
                        cell1.setCellType(CellType.STRING);
                        user.setUname(cell1.getStringCellValue());
                    }

                    Cell cell2 = row.getCell(2);
                    if(cell2!=null){
                        cell2.setCellType(CellType.STRING);
                        user.setUpassword(cell2.getStringCellValue());
                    }

                    Cell cell3 = row.getCell(3);
                    if(cell3!=null){
                        cell3.setCellType(CellType.STRING);
                        user.setUphone(cell3.getStringCellValue());
                    }

                    Cell cell4 = row.getCell(4);
                    if(cell4!=null){
                        cell4.setCellType(CellType.STRING);
                        user.setUpicture(cell4.getStringCellValue());
                    }

                    //5、保存用户
                    userDAO.add(user);
                }
            }
            workbook.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
