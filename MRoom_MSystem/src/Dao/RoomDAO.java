package Dao;

import Po.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAO extends BaseHibernateDAO {
    //查找
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
        String hql = "from Room r order by r.rid asc";
        try {
            //设置分页
            Query query = getSession().createQuery(hql).setFirstResult((page - 1) * limit).setMaxResults(limit);
            List<Room> list = query.list();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    //返回信息总条数
    public Long roomCount() {
        String hql = "select count(*) from Room";
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

    public void del(String rid) {
        Transaction tran = null;
        Session session = getSession();
        String hql = "delete from Room r where r.rid=:rid";
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("rid", rid);
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

    public void update(String rid, int rnum, String rstate, String raddress) {
        Transaction tran = null;
        Session session = getSession();
        String hql = "update Room r set r.rnum=:rnum,r.rstate=:rstate,r.raddress=:raddress where r.rid=:rid";
        try {
            tran = session.beginTransaction();
            Query query = session.createQuery(hql).setInteger("rnum", rnum)
                    .setParameter("rstate", rstate)
                    .setParameter("raddress", raddress)
                    .setParameter("rid", rid);
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

    //增加，未定，由于hql不支持insert into
    public void add(Room room) {
        Transaction tran = null;
        Session session = getSession();
        try {
            tran = session.beginTransaction();
            session.save(room);
            session.flush();
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
