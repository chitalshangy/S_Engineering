package Dao;

import Po.Admin;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDAO extends BaseHibernateDAO implements IAdminDAO{
    //登录
    public List findByhql(String hql){
        Transaction tran=null;
        //直接getSession()，否则报空指针错误
        Session session=getSession();
        List list=null;
        try{
            tran=session.beginTransaction();
            Query query=session.createQuery(hql);
            list=query.list();
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
        return list;
    }
    //查找全部
    public List findAll(int page,int limit){
        String hql = "from Admin a order by a.aid asc";
        try {
            Query query=getSession().createQuery(hql);
            List<Admin> list =query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    //返回信息总条数
    public Long infoCount(){
        String hql="select count(*) from Admin";
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
}
