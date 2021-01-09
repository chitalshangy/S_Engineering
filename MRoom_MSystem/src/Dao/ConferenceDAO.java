package Dao;

import Po.Conference;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Time;
import java.util.List;

public class ConferenceDAO extends BaseHibernateDAO implements IConferenceDAO {

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


    public List findAll(int page, int limit, String uid) {
        String hql = "select r from Conference c,Reserve r where c.user.uid=:uid and r.state='1' and c.reserve.reid=r.reid order by r.date asc";
        try {
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

    public Long infoCount(String uid) {
        String hql = "select count(*) from Conference c,Reserve r where c.user.uid=:uid and r.state='1' and c.reserve.reid=r.reid";
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

    public List findAllPo(String reid, int page, int limit) {
        String hql = "select c.user from Conference c where c.reserve.reid=:reid ";
        try {
            Query query = getSession().createQuery(hql);
            query.setString("reid", reid);
            query.setFirstResult((page - 1) * limit);
            query.setMaxResults(limit);
            List list = query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public Long infoCountPo(String reid) {
        String hql = "select count(*) from Conference c where c.reserve.reid=:reid";
        Transaction tran = null;
        Session session = getSession();
        Long tem = 0l;
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setString("reid", reid);
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

    public void addConference(Conference conference) {
        Session session = getSession();
        Transaction tran = session.beginTransaction();
        try {
            session.save(conference);
            session.flush();
            tran.commit();
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            session.close();
        }
    }

    public void deleteConference(Conference conference) {
        Session session = getSession();
        Transaction tran = session.beginTransaction();
        try {
            session.delete(conference);
            session.flush();
            tran.commit();
        } catch (RuntimeException re) {
            if (tran != null) tran.rollback();
            throw re;
        } finally {
            session.close();
        }
    }

    public void deleteConference(String reid) {
        Transaction tran = null;
        Session session = getSession();
        try {
            tran = session.beginTransaction();
            String hql = "delete from Conference c where c.reserve.reid=:reid";
            Query query = session.createQuery(hql);
            query.setString("reid", reid);
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

    public void checkInConference(String reid, String uid, Time time) {
        Transaction tran = null;
        Session session = getSession();
        try {
            tran = session.beginTransaction();
            List list = findByhql("from Conference where checkInTime is null and reid = '" + reid + "'");
            if (list.size() == 0) return;
            String hql4 = "update Conference set checkInTime=:checkInTime where uid=:uid and reid=:reid";
            Query query = session.createQuery(hql4);
            query.setParameter("checkInTime", time);
            query.setParameter("uid", uid);
            query.setParameter("reid", reid);
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
}