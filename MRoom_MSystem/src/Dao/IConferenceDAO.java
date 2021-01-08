package Dao;

import Po.Conference;

import java.sql.Time;
import java.util.List;

public interface IConferenceDAO {

    //查找
    public List findByhql(String hql);

    //增加会议记录
    public void addConference(Conference conference);

    //删除会议记录
    public void deleteConference(Conference conference);

    //删除会议记录的延展
    public void deleteConference(String reid);

    //查找全部
    public List findAll(int page, int limit, String uid);

    //返回个数
    public Long infoCount(String uid);

    //查找与会人员
    public List findAllPo(String reid, int page, int limit);

    //返回与会人员数量
    public Long infoCountPo(String reid);

    //签到
    public void checkInConference(String reid, String uid, Time time);
}
