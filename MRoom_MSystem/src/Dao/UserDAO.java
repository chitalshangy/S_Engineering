package Dao;

import Po.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO extends BaseHibernateDAO implements IUserDAO {
    //登录查找
    public List findByhql(String hql) {
        Transaction tran = null;
        //直接getSession()，否则报空指针错误
        Session session = getSession();
        List list = null;
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql);
            list = query.list();
            tran.commit();
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }

    //查找全部
    public List findAll(int page, int limit) {
        String hql = "from User u order by u.uid asc";
        try {
            //设置分页
            Query query = getSession().createQuery(hql).setFirstResult((page - 1) * limit).setMaxResults(limit);
            List<User> list = query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    //返回信息总条数
    public Long userCount() {
        String hql = "select count(*) from User";
        Transaction tran = null;
        Session session = getSession();
        Long tem = 0l;
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql);
            tem = (Long) query.uniqueResult();
            tran.commit();
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tem;
    }

    //删除
    public void del(String uid) {
        Transaction tran = null;
        Session session = getSession();
        String hql = "delete from User u where u.uid=:uid";
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("uid", uid);
            query.executeUpdate();
            tran.commit();
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    //更新
    public void update(String uid, String uname, String upassword, String uphone) {
        Transaction tran = null;
        Session session = getSession();
        String hql = "update User u set u.uname=:uname,u.upassword=:upassword,u.uphone=:uphone where u.uid=:uid";
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("uname", uname)
                    .setParameter("upassword", upassword)
                    .setParameter("uphone", uphone)
                    .setParameter("uid", uid);
            query.executeUpdate();
            tran.commit();
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    //添加
    public void add(User user){
        Transaction tran = null;
        Session session = getSession();
        try {
            tran = session.beginTransaction();
            session.save(user);
            tran.commit();
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
