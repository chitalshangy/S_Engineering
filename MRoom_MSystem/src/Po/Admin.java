package Po;

public class Admin {
    private String aid;
    private String apassword;
    private String aphone;

    //记得构造函数
    public Admin() {
    }

    public Admin(String aid, String apassword, String aphone) {
        this.aid = aid;
        this.apassword = apassword;
        this.aphone = aphone;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }
}
