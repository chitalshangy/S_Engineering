package Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class BaseHibernateDAO {
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //登录查找
    public abstract List findByhql(String hql);

    //查找全部
    public abstract List findAll(int page, int limit);

    //返回信息总条数
    public abstract Long infoCount();
}
