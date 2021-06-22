package andriod;
import Po.Reserve;
import Po.Room;
import Po.User;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet("/SearchMyAttendServlet")
public class SearchMyAttendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user=new User();
        String uid = request.getParameter("uid");
        user.setUid(uid);
        System.out.println(uid);
        JSONObject json = new JSONObject();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        String driver = "com.mysql.jdbc.Driver";
        String dburl = "jdbc:mysql://localhost:3306/mroom_msystem";
        String dbusername = "dbuser";
        String dbpassword = "dbpassword";
        List<Reserve> list=new ArrayList<Reserve>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dburl, dbusername, dbpassword);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM conference WHERE uid=? and cidentity='参会者'");
            pstmt.setString(1, uid);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                String reid=rst.getString("reid");
                Reserve reserve=new Reserve();
                reserve.setReid(reid);
                try {
                    Class.forName(driver);
                    Connection conn2 = DriverManager.getConnection(dburl, dbusername, dbpassword);
                    PreparedStatement pstmt2 = conn2.prepareStatement("SELECT * FROM reserve WHERE reid=?");
                    pstmt2.setString(1, reid);
                    ResultSet rst2 = pstmt2.executeQuery();
                    while (rst2.next()) {
                        String uid2=rst2.getString("uid");
                        String rid=rst2.getString("rid");
                        String state=rst2.getString("state");
                        String title=rst2.getString("title");
                        Date date=rst2.getDate("date");
                        User user2=new User();
                        user2.setUid(uid2);
                        reserve.setUser(user2);
                        reserve.setUid(user2.getUid());
                        Room room=new Room();
                        room.setRid(rid);
                        reserve.setRoom(room);
                        reserve.setRid(room.getRid());
                        reserve.setState(state);
                        reserve.setTitle(title);
                        reserve.setDate(date);
                        list.add(reserve);
                    }
                    pstmt2.close();
                    conn2.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        JsonConfig config = new JsonConfig();
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
        JSONArray jsonArray = new JSONArray();
        jsonArray = jsonArray.fromObject(list,config);
        System.out.println(jsonArray.toString());
        response.getWriter().write(jsonArray.toString());
    }
}