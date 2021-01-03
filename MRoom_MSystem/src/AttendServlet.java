import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AttendServlet")
public class AttendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid=request.getParameter("uid");
        String reid=request.getParameter("reid");
        String status=null;
        System.out.println(uid);
        System.out.println(reid);
        JSONObject json = new JSONObject();
        response.setContentType("text/html,charset=utf=8");
        String driver="com.mysql.jdbc.Driver";
        String dburl="jdbc:mysql://localhost:3306/mroom_msystem";
        String dbusername="dbuser";
        String dbpassword="dbpassword";
        try{
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(dburl,dbusername,dbpassword);
            PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM conference WHERE uid=? and reid=?");
            pstmt.setString(1,uid);
            pstmt.setString(2,reid);
            ResultSet rst=pstmt.executeQuery();
            if(rst.next()){
                status="already";
                pstmt.close();
                conn.close();
            }
            else{
                try{
                    Class.forName(driver);
                    conn= DriverManager.getConnection(dburl,dbusername,dbpassword);
                    pstmt=conn.prepareStatement("Insert into conference(uid,reid,cidentity) values(?,?,?)");
                    pstmt.setString(1,uid);
                    pstmt.setString(2,reid);
                    pstmt.setString(3,"参会者");
                    int rst2=pstmt.executeUpdate();
                    if(rst2>0){
                        status="true";
                    }
                    else{
                        status="false";
                    }
                    pstmt.close();
                    conn.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
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