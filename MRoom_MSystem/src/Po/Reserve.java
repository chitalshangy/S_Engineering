package Po;

import java.sql.Time;
import java.util.Date;

public class Reserve {
    private int reid;
    private String uid;
    private int rid;
    private  String rephone;
    private String state;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String state2;
    public int getReid() {
        return reid;
    }

    public void setReid(int reid) {
        this.reid = reid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRephone() {
        return rephone;
    }

    public void setRephone(String rephone) {
        this.rephone = rephone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }
}
