package Dao;

import Po.Conference;

import java.sql.Time;
import java.util.List;

public interface IConferenceDAO {
    public List findByhql(String hql);

    public void addConference(Conference conference);

    public void deleteConference(Conference conference);

    public void deleteConference(String reid);

    public List findAll(int page, int limit, String uid);

    public Long infoCount(String uid);

    public List findAllPo(String reid, int page, int limit);

    public Long infoCountPo(String reid);

    public void checkInConference(String reid, String uid, Time time);
}
