package Service;

import Po.Reserve;

import java.text.ParseException;
import java.util.List;

public interface IReserveService {

    //紧急预约
    public boolean emergencyReserve(int num, Reserve reserve);

    //预约
    public boolean addReserve(Reserve reserve);

    //删除预约
    public void deleteReserve(String reid);

    //获得所有信息
    public List InfoList(int page, int limit);

    //获取总行数
    public long Count();

    //获得我预约的会议
    public List InfoMyList(int page, int limit);

    //获取我预约的会议的行数
    public long CountMy();
}
