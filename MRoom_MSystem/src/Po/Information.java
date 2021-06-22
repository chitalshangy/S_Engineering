package Po;

public class Information {
    private String uid;
    private String itext;
    private String istate;
    private User user;
    public Information() {
    }

    public Information(String uid, String itext, String istate) {
        this.uid = uid;
        this.itext = itext;
        this.istate = istate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getItext() {
        return itext;
    }

    public void setItext(String itext) {
        this.itext = itext;
    }

    public String getIstate() {
        return istate;
    }

    public void setIstate(String istate) {
        this.istate = istate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
