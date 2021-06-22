package Dao;

import Po.Information;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class InfomationDAO extends BaseHibernateDAO implements IInfomationDAO{
    public void insert(String uid, String itext, String istate) {
        String driver = "com.mysql.jdbc.Driver";
        String dburl = "jdbc:mysql://localhost:3306/mroom_msystem";
        String dbusername = "dbuser";
        String dbpassword = "dbpassword";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dburl, dbusername, dbpassword);
            PreparedStatement pstmt = conn.prepareStatement("insert into information values(?,?,?)");
            pstmt.setString(1, uid);
            pstmt.setString(2, itext);
            pstmt.setString(3,istate);
            int rst = pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}