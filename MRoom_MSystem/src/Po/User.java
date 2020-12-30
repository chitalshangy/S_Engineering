package Po;

import java.awt.*;

public class User {
    private String uid;
    private String uname;
    private String upassword;
    private String uidentity;
    private String upicture;

    //记得构造函数
    public User() {
    }

    public User(String uid, String uname, String upassword, String uidentity, String upicture) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.uidentity = uidentity;
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

    public void setUidentity(String uidentity) {
        this.uidentity = uidentity;
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

    public String getUidentity() {
        return uidentity;
    }

    public String getUpicture() {
        return upicture;
    }

    public void setUpicture(String upicture) {
        this.upicture = upicture;
    }
}
