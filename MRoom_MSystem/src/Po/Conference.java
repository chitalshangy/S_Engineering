package Po;

import java.io.Serializable;

public class Conference implements Serializable {
    private String uid;
    private String reid;
    private String cidentity;
    private String checkInTime;
    private String signingOutTime;
    private User user;
    private Reserve reserve;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReid() {
        return reid;
    }

    public void setReid(String reid) {
        this.reid = reid;
    }

    public String getCidentity() {
        return cidentity;
    }

    public void setCidentity(String cidentity) {
        this.cidentity = cidentity;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getSigningOutTime() {
        return signingOutTime;
    }

    public void setSigningOutTime(String signingOutTime) {
        this.signingOutTime = signingOutTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }
}
