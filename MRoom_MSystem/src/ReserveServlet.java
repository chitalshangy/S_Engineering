import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Dao.ReserveDAO;
import Po.Reserve;
import Po.Room;
import Po.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.buffer.DataBufferUtils;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid=request.getParameter("uid");
        String number=request.getParameter("number");
        int num=Integer.parseInt(number);
        String phone=request.getParameter("phone");
        String reid=null;
        System.out.println(uid);
        System.out.println(num);
        System.out.println(phone);
        Reserve reserve=new Reserve();
        reserve.setUid(uid);
        reserve.setRephone(phone);
        JSONObject json = new JSONObject();
        response.setContentType("text/html,charset=utf=8");
        ArrayList<Room> list =new ArrayList<Room>();
        try {
            list = findBynum(num);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        if (list.isEmpty()) {
            try {
                reid="false2";
                json.put("reid", reid);
                System.out.println(json.toString());
                response.getWriter().write(json.toString());
                return;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        java.util.Date now = new java.util.Date();
        // java.util.Date -> java.time.LocalDate
        System.out.println(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(now));
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // java.time.LocalDate -> java.sql.Date
        Date date = java.sql.Date.valueOf(localDate);
        System.out.println(date);
        System.out.println(sdf.format(date));

        Long time = System.currentTimeMillis();
        Time start = new Time(time);
        time += 30 * 1000 * 60;
        Time end = new Time(time);

        reserve.setDate(date);
        reserve.setStartTime(start);
        reserve.setEndTime(end);
        reserve.setState("1");
        reserve.setTitle("紧急会议");
        System.out.println(reserve.toString());
        for (int i = 0; i < list.size(); ++i) {
            Room r = list.get(i);
            String rid = r.getRid();
            reserve.setRoom(r);
            if (!judge(rid, date, reserve.getStartTime(), reserve.getEndTime())) continue;
            else {
                reid= uid + rid + date + start;
                reserve.setReid(reid);
                reserve.setRid(rid);
                save(reserve);
                System.out.println(reid);
                try {
                    json.put("reid", reid);
                    System.out.println(json.toString());
                    response.getWriter().write(json.toString());
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        reid="false";
        try {
            json.put("reid", reid);
            System.out.println(json.toString());
            response.getWriter().write(json.toString());
            return;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Room> findBynum(int num) throws SQLException, ClassNotFoundException {
        ArrayList<Room> list = new ArrayList<Room>();
        try {
            System.out.println("开始查询");
            String driver="com.mysql.jdbc.Driver";
            String dburl="jdbc:mysql://localhost:3306/mroom_msystem";
            String dbusername="root";
            String dbpassword="123";
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(dburl,dbusername,dbpassword);
            System.out.println("查询1");
            PreparedStatement pstmt=conn.prepareStatement("SELECT * from room where rnum >= ? ");
            pstmt.setInt(1,num);
            ResultSet rst=pstmt.executeQuery();
            System.out.println("查询2");
            while(rst.next()){
                System.out.println("查询3");
                Room room=new Room();
                room.setRid(rst.getString("rid"));
                room.setRnum(rst.getInt("rnum"));
                room.setRstate(rst.getString("rstate"));
                room.setRaddress(rst.getString("raddress"));
                System.out.println(room.getRid());
                System.out.println(room.getRnum());
                System.out.println(room.getRstate());
                System.out.println(room.getRaddress());
                list.add(room);
                System.out.println("查询完成1");
            }
            System.out.println("查询完成2");
            pstmt.close();
            conn.close();
            return list;
        } catch (RuntimeException | ClassNotFoundException | SQLException re) {
            throw re;
        }
    }
    public boolean judge(String rid, Date date, Time start, Time end) {
        String driver="com.mysql.jdbc.Driver";
        String dburl="jdbc:mysql://localhost:3306/mroom_msystem";
        String dbusername="root";
        String dbpassword="123";
        try{
            System.out.println("开始查询");
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(dburl,dbusername,dbpassword);
            PreparedStatement pstmt=conn.prepareStatement("select * from reserve r where r.state='1' and r.rid= ? and r.date= ? and ? >=r.startTime and ? <r.endTime");
            PreparedStatement pstmt2=conn.prepareStatement("select  * from reserve r where r.state='1' and r.rid= ? and r.date= ? and ? >r.startTime and ? <=r.endTime");
            pstmt.setString(1,rid);
            pstmt.setDate(2,date);
            pstmt.setTime(3,start);
            pstmt.setTime(4,start);
            pstmt2.setString(1,rid);
            pstmt2.setDate(2,date);
            pstmt2.setTime(3,end);
            pstmt2.setTime(4,end);
            ResultSet rst=pstmt.executeQuery();
            ResultSet rst2=pstmt2.executeQuery();
            System.out.println("查询完成");
            if((!rst.next()) && (!rst2.next())){
                return true;
            }
            else{
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void save(Reserve reserve) {
        String driver="com.mysql.jdbc.Driver";
        String dburl="jdbc:mysql://localhost:3306/mroom_msystem";
        String dbusername="root";
        String dbpassword="123";
        try{
            System.out.println("开始插入");
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(dburl,dbusername,dbpassword);
            PreparedStatement pstmt=conn.prepareStatement("Insert into reserve(reid,uid,rid,rephone,state,title,date,starttime,endtime,open) values(?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1,reserve.getReid());
            pstmt.setString(2,reserve.getUid());
            pstmt.setString(3,reserve.getRid());
            pstmt.setString(4,reserve.getRephone());
            pstmt.setString(5,reserve.getState());
            pstmt.setString(6,reserve.getTitle());
            pstmt.setDate(7,reserve.getDate());
            pstmt.setTime(8,reserve.getStartTime());
            pstmt.setTime(9,reserve.getEndTime());
            pstmt.setString(10,reserve.getOpen());
            PreparedStatement pstmt2=conn.prepareStatement("Insert into conference(uid,reid,cidentity) values(?,?,?)");
            pstmt2.setString(1,reserve.getUid());
            pstmt2.setString(2,reserve.getReid());
            pstmt2.setString(3,"预约者");
            int rst=pstmt.executeUpdate();
            int rst2=pstmt2.executeUpdate();
            if(rst>0&&rst2>0){
                System.out.println("插入成功");
            }
            else{
                System.out.println("插入失败");
            }
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
