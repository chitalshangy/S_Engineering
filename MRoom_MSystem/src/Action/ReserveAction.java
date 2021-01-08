package Action;

import Po.Reserve;
import Service.IReserveService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class ReserveAction {
    private Reserve reserve;
    private int num;//紧急预约时存储参会人数
    private IReserveService reserveService;

    public Reserve getReserve() {
        return reserve;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public void setReserveService(IReserveService reserveService) {
        this.reserveService = reserveService;
    }

    public String addReserve() {
        if (reserveService.addReserve(reserve)) return "success";
        else return "fail";
    }

    public String emergencyReserve() {
        if (reserveService.emergencyReserve(num, reserve)) return "success";
        else return "fail";
    }

    public String deleteReserve() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String reid = request.getParameter("reid");
        reserveService.deleteReserve(reid);
        return "success";
    }
}