package Action;

import Po.Conference;
import Service.IConferenceService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConferenceAction {
    private Conference conference;
    private IConferenceService conferenceService = null;

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public void setConferenceService(IConferenceService conferenceService) {
        this.conferenceService = conferenceService;
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

    public void checkIn() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String a = request.getParameter("image");
        String rid = request.getParameter("rid");
        conferenceService.checkIn(a, rid);
    }
}
