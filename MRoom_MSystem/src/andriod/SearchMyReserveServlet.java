package andriod;
import Po.Reserve;
import Po.Room;
import Po.User;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
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

@WebServlet("/SearchMyReserveServlet")
public class SearchMyReserveServlet extends HttpServlet {
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM reserve WHERE uid=?");
            pstmt.setString(1, uid);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                String reid=rst.getString("reid");
                Reserve reserve=new Reserve();
                reserve.setReid(reid);
                String rid=rst.getString("rid");
                String state=rst.getString("state");
                String title=rst.getString("title");
                Date date=rst.getDate("date");
                reserve.setUser(user);
                reserve.setUid(user.getUid());
                Room room=new Room();
                room.setRid(rid);
                reserve.setRoom(room);
                reserve.setRid(room.getRid());
                reserve.setState(state);
                reserve.setTitle(title);
                reserve.setDate(date);
                System.out.println(reserve.toString());
                list.add(reserve);
                System.out.println(list.toString());
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

class JsonDateValueProcessor implements JsonValueProcessor {
    private String pattern = "yyyy-MM-dd";

    public Object processArrayValue(Object value, JsonConfig config) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value, JsonConfig config) {
        return process(value);
    }
    private Object process(Object value){
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.UK);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }
}