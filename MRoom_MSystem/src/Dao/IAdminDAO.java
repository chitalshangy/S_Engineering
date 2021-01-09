package Dao;

import Po.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public interface IAdminDAO {
    //登录
    public List findByhql(String hql);

    //查找全部
    public List findAll(int page, int limit);

    //返回信息总条数
    public Long infoCount();

    //管理员信息更新
    public void update(String aid, String apassword, String aphone);
}
