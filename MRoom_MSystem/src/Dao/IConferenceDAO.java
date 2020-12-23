package Dao;

import Po.Conference;

import java.util.List;

public interface IConferenceDAO {
    public List findByhql(String hql);
    public void addConference(Conference conference);
    public void deleteConference(Conference conference);
    public  List findAll(int page,int limit,String uid);
    public  Long infoCount(String uid);
}
