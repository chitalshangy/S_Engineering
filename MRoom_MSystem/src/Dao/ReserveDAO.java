package Dao;

import Po.Reserve;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReserveDAO extends BaseHibernateDAO implements IReserveDAO {
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

    public List findAll(int page,int limit){
        Date date = new Date(System.currentTimeMillis());
        System.out.print("date   ");
        System.out.println(date);
        String d=new SimpleDateFormat("yyyy-MM-dd").format(date);
        String hql = "from Reserve r where r.date>="+d+" order by r.room.rid asc ";
        try {
            //设置分页
            Query query=getSession().createQuery(hql).setFirstResult((page-1)*limit).setMaxResults(limit);
            List list =query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public Long infoCount(){
        Date date = new Date(System.currentTimeMillis());
        String hql="select count(*) from Reserve r where r.date>="+date+" and r.state!='0'";
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


    public List findMyAll(int page,int limit,String uid){

        String hql = "from Reserve r where r.user.uid="+uid+" order by r.room.rid asc ";
        try {
            //设置分页
            Query query=getSession().createQuery(hql).setFirstResult((page-1)*limit).setMaxResults(limit);
            List list =query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public Long infoMyCount(String uid){

        String hql="select count(*) from Reserve r where r.user.uid="+uid;
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

    public void save(Reserve reserve){
        Transaction tran=null;
        Session session=getSession();
        try{
            tran=session.beginTransaction();
            session.save(reserve);
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
    }

    public void deleteReserve(String reid){
        Transaction tran=null;
        Session session=getSession();
        try {
            tran=session.beginTransaction();
            String hql="update Reserve r set r.state=:state where r.reid=:reid";
            Query queryupdate=session.createQuery(hql);
            queryupdate.setString("state","0");
            queryupdate.setString("reid",reid);
            queryupdate.executeUpdate();
            tran.commit();
        } catch (RuntimeException re) {
            if(tran != null) tran.rollback();
            throw re;
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

}
