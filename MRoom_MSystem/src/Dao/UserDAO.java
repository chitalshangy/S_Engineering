package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO extends BaseHibernateDAO implements IUserDAO{
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
}
