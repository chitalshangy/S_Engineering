package Action;

import Po.Conference;
import Service.IConferenceService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ConferenceAction {
    private Conference conference;
    private IConferenceService conferenceService = null;
    JSONObject data;

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public void setConferenceService(IConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONObject getData() {
        return data;
    }

    public String addConference() {
        conferenceService.addConference(conference);
        return "success";
    }

    public String deleteConference() {
        HttpServletRequest reqeust = ServletActionContext.getRequest();
        String reid = reqeust.getParameter("reid");
        conferenceService.deleteConference(reid);
        return "success";
    }

    public String deleteConferenceByManager() {
        HttpServletRequest reqeust = ServletActionContext.getRequest();
        String uid = reqeust.getParameter("uid");
        conferenceService.deleteConferenceByManager(uid);
        return "success";
    }

    public String checkIn() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String a = request.getParameter("image");
        String rid = request.getParameter("rid");
        data = new JSONObject();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count",0);
        if(conferenceService.checkIn(a, rid)){
            data.put("out", "check in success!");
        }
        else{
            data.put("out", "check in fail!");
        }
        return "success";
    }
}
