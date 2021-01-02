package Dao;

import Po.Reserve;
import Po.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public interface IRoomDAO {
    public List findByhql(String hql);
    public List findAll(int page,int limit);
    public Long infoCount();
    public void del(String rid);
    public void update(String rid,int rnum,String rstate,String raddress);
    public void add(Room room);
}
