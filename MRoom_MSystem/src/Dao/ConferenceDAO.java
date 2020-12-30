package Dao;

import Po.Admin;
import Po.Conference;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ConferenceDAO extends BaseHibernateDAO implements IConferenceDAO {

    public List findByhql(String hql){
        Transaction tran=null;
        Session session=getSession();
        List list=null;
        try{
            tran=session.beginTransaction();
            Query query=session.createQuery(hql);
            list=query.list();
            tran.commit();
            return list;
        }
        catch (RuntimeException re){
            if(tran != null) tran.rollback();
            throw re;
        }
        finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public List findAll(int page, int limit) {
        return null;
    }

    @Override
    public Long infoCount() {
        return null;
    }

    public List findAll(int page,int limit,String uid){
        String hql = "select r from Conference c,Reserve r where c.user.uid="+uid+" and c.reserve.reid=r.reid order by r.date asc";
        try {
            Query query=getSession().createQuery(hql);
            List list =query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public Long infoCount(String uid){
        String hql="select count(*) from Conference c,Reserve r where c.user.uid="+uid+" and c.reserve.reid=r.reid";
        Transaction tran=null;
        Session session=getSession();
        Long tem=0l;
        try{
            tran=session.beginTransaction();
            Query query=session.createQuery(hql);
            tem=(Long)query.uniqueResult();
            tran.commit();
        }
        catch (RuntimeException re){
            if(tran != null) tran.rollback();
            throw re;
        }
        finally{
            if(session!=null){
                session.close();
            }
        }
        return tem;
    }

    public void addConference(Conference conference){
        Session session=getSession();
        Transaction tran=session.beginTransaction();
        try {
            session.save(conference);
            session.flush();
            tran.commit();
        } catch (RuntimeException re) {
            if(tran != null) tran.rollback();
            throw re;
        }finally {
            session.close();
        }
    }
    public void deleteConference(Conference conference){
        Session session=getSession();
        Transaction tran=session.beginTransaction();
        try {
            session.delete(conference);
            session.flush();
            tran.commit();
        } catch (RuntimeException re) {
            if(tran != null) tran.rollback();
            throw re;
        }finally {
            session.close();
        }
    }


}
