package Po;

import java.io.Serializable;
import java.sql.Time;

public class Conference implements Serializable {
    private String uid;
    private String reid;
    private String cidentity;
    private Time checkInTime;
    private Time signingOutTime;
    private User user;
    private Reserve reserve;

    public Conference(){

    }

    public Conference(User user, Reserve reserve) {
        this.user = user;
        this.reserve = reserve;
    }

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

    public Time getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Time checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Time getSigningOutTime() {
        return signingOutTime;
    }

    public void setSigningOutTime(Time signingOutTime) {
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
