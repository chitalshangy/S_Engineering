package Po;

public class Room {
    private String rid;
    private int rnum;
    private String rstate;
    private String raddress;

    public Room() {

    }

    public Room(String rid) {
        this.rid = rid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    public String getRstate() {
        return rstate;
    }

    public void setRstate(String rtype) {
        this.rstate = rtype;
    }

    public String getRaddress() {
        return raddress;
    }

    public void setRaddress(String raddress) {
        this.raddress = raddress;
    }

}
