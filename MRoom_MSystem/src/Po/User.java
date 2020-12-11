package Po;

import java.awt.*;

public class User {
    private String uid;
    private String uname;
    private String upassword;
    private String uidentity;
    private Image image;

    public User() {
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User(String uid, String uname, String upassword, String uidentity, Image image) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.uidentity = uidentity;
        this.image = image;
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
    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
