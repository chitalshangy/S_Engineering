package Action;

import Po.Reserve;
import Service.IReserveService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ReserveAction {
    private Reserve reserve;
    private IReserveService reserveService=null;

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public void setReserveService(IReserveService reserveService) {
        this.reserveService = reserveService;
    }



    public String addReserve(){
        if(reserveService.addReserve(reserve)) return "success";
        else return "fail";
    }



    public String deleteReserve(){
        HttpServletRequest reqeust= ServletActionContext.getRequest();
        String reid=reqeust.getParameter("reid");
        reserveService.deleteReserve(reid);
        return "success";
    }

}
