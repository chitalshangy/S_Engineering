package Dao;

import java.util.List;

public interface IUserDAO {
    //登录
    public List findByhql(String hql);
}
