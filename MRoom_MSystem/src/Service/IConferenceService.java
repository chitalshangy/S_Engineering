package Service;

import Po.Conference;

import java.io.IOException;
import java.util.List;

public interface IConferenceService {

    //添加会议记录
    public void addConference(Conference conference);

    //查找当前用户参加的所有会议
    public List InfoList(int page, int limit);

    //返回记录个数
    public long Count();

    //删除当前用户预约流水号为reid的会议记录
    public void deleteConference(String reid);

    //会议预约人员删除会议
    public void deleteConferenceByManager(String uid);

    //会议预约人员查找参加会议的人员
    public List InfoListPa(String reid, int page, int limit);

    //返回会议预约人员查找参加会议的人员个数
    public long CountPa(String reid);

    //会议签到
    public Boolean checkIn(String a,String rid) throws IOException;
}
