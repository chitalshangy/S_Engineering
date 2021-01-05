package andriod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid = request.getParameter("uid");
        String password = request.getParameter("password");
        String status = null;
        System.out.println(uid);
        System.out.println(password);
        JSONObject json = new JSONObject();
        response.setContentType("text/html,charset=utf=8");
        String driver = "com.mysql.jdbc.Driver";
        String dburl = "jdbc:mysql://localhost:3306/mroom_msystem";
        String dbusername = "dbuser";
        String dbpassword = "dbpassword";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dburl, dbusername, dbpassword);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE uid=? and upassword=?");
            pstmt.setString(1, uid);
            pstmt.setString(2, password);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                status = "true";
            } else {
                status = "false";
            }
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(status);
        try {
            json.put("status", status);
            System.out.println(json.toString());
            response.getWriter().write(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
