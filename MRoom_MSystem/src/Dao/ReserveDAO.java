package Dao;

import Po.Reserve;
import javafx.beans.binding.LongExpression;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

public class ReserveDAO extends BaseHibernateDAO implements IReserveDAO {
    public List findByhql(String hql) {
        Transaction tran = null;
        Session session = getSession();
        List list = null;
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql);
            list = query.list();
            tran.commit();
            return list;
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean judge(String rid, Date date, Time start, Time end) {
        String hql = "from Reserve r where r.state='1' and r.room.rid=:rid " +
                "and r.date=:date and :start>=r.startTime and :start<r.endTime";
        String hql2 = "from Reserve r where r.state='1' and r.room.rid=:rid " +
                "and r.date=:date and :end>r.startTime and :end<=r.endTime";
        String hql3 = "select r.rstate from Room r where r.rid=:rid";

        String tem_id;

        Transaction tran = null;
        Session session = getSession();
        List list = null;
        List list2 = null;
        try {
            tran = session.beginTransaction();
            Query query_tem = session.createQuery(hql3);
            query_tem.setParameter("rid", rid);
            tem_id = (String) query_tem.uniqueResult();
            if (tem_id.equals("1")) {
                return false;
            }
            Query query = session.createQuery(hql);
            query.setString("rid", rid);
            query.setTimestamp("date", date);
            query.setTime("start", start);
            list = query.list();
            Query query2 = session.createQuery(hql2);
            query2.setString("rid", rid);
            query2.setTimestamp("date", date);
            query2.setTime("end", end);
            list2 = query2.list();
            tran.commit();
            if (list.isEmpty() && list2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List findAll(int page, int limit) {

        String hql = "from Reserve r where r.date>=current_date and r.state = '1' order by r.room.rid asc ";

        try {
            //设置分页
            Query query = getSession().createQuery(hql);
            query.setFirstResult((page - 1) * limit);
            query.setMaxResults(limit);
            List list = query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public Long infoCount() {

        String hql = "select count(*) from Reserve r where r.date>=current_date and r.state!='0'";
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

    public List history(int page, int limit) {

        String hql = "from Reserve";
        try {
            //设置分页
            Query query = getSession().createQuery(hql);
            query.setFirstResult((page - 1) * limit);
            query.setMaxResults(limit);
            List list = query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public Long historyCount() {

        String hql = "select count(*) from Reserve";
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

    public List findMyAll(int page, int limit, String uid) {

        String hql = "from Reserve r where r.user.uid=:uid order by r.room.rid asc ";
        try {
            //设置分页
            Query query = getSession().createQuery(hql);
            query.setString("uid", uid);
            query.setFirstResult((page - 1) * limit);
            query.setMaxResults(limit);
            List list = query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public Long infoMyCount(String uid) {

        String hql = "select count(*) from Reserve r where r.user.uid=:uid";
        Transaction tran = null;
        Session session = getSession();
        Long tem = 0l;
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setString("uid", uid);
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

    public void save(Reserve reserve) {
        Transaction tran = null;
        Session session = getSession();
        try {
            tran = session.beginTransaction();
            session.save(reserve);
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

    public void deleteReserve(String reid) {
        Transaction tran = null;
        Session session = getSession();
        try {
            tran = session.beginTransaction();
            String hql = "update Reserve r set r.state=:state where r.reid=:reid";
            Query queryupdate = session.createQuery(hql);
            queryupdate.setString("state", "0");
            queryupdate.setString("reid", reid);
            queryupdate.executeUpdate();
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

    public void updateReserve(String reid){
        Transaction tran = null;
        Session session = getSession();
        try {
            tran = session.beginTransaction();
            String hql = "update Reserve r set r.state=:state where r.reid=:reid";
            Query queryupdate = session.createQuery(hql);
            queryupdate.setString("state", "2");
            queryupdate.setString("reid", reid);
            queryupdate.executeUpdate();
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