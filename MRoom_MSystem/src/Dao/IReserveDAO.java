package Dao;

import Po.Reserve;
import org.hibernate.query.Query;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

public interface IReserveDAO {

    public List findByhql(String hql);

    public void save(Reserve reserve);

    public void deleteReserve(String reid);

    public List findAll(int page, int limit);

    public Long infoCount();

    public List history(int page, int limit);

    public Long historyCount();

    public List findMyAll(int page, int limit, String uid);

    public Long infoMyCount(String uid);

    public boolean judge(String rid, Date date, Time start, Time end);

    public void updateReserve(String reid);
}
