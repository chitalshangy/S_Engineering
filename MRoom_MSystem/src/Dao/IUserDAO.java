package Dao;

import Po.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public interface IUserDAO {
    //登录查找
    public List findByhql(String hql);

    //查找全部
    public List findAll(int page, int limit);

    //返回信息总条数
    public Long userCount();

    public void del(String uid);

    //更新
    public void update(String uid, String uname, String upassword, String uphone);

    //添加
    public void add(User user);
}
