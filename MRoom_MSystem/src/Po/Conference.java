package Po;

import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

public class Conference implements Serializable {
    private String uid;
    private int reid;
    private String cidentity;
    private Time checkInTime;
    private Time signingOutTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference that = (Conference) o;
        return reid == that.reid && Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, reid);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getReid() {
        return reid;
    }

    public void setReid(int reid) {
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
}
