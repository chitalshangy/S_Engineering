package Po;

import java.awt.*;

public class User {
    private String uid;
    private String uname;
    private String upassword;
    private String uphone;
    private String upicture;

    //记得构造函数
    public User() {
    }

    public User(String uid) {
        this.uid = uid;
    }

    public User(String uid, String uname, String upassword, String uphone, String upicture) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.uphone = uphone;
        this.upicture = upicture;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getUpassword() {
        return upassword;
    }


    public String getUpicture() {
        return upicture;
    }

    public void setUpicture(String upicture) {
        this.upicture = upicture;
    }
}
