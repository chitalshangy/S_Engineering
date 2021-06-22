package andriod;
import Po.Information;
import Po.User;
import net.sf.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InforServlet")
public class InforServlet extends HttpServlet {
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
        List<Information> list=new ArrayList<Information>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dburl, dbusername, dbpassword);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM information WHERE uid=?");
            pstmt.setString(1, uid);
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()) {
                String itext=rst.getString("itext");
                String istate=rst.getString("istate");
                Information information=new Information();
                information.setUser(user);
                information.setItext(itext);
                information.setIstate(istate);
                information.setUid(user.getUid());
                System.out.println(information.toString());
                list.add(information);
                System.out.println(list.toString());
            }
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray.toString());
        response.getWriter().write(jsonArray.toString());
    }
}