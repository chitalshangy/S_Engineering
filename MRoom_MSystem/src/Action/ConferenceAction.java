package Action;

import Po.Conference;
import Service.IConferenceService;

public class ConferenceAction {
    private Conference conference;
    private IConferenceService conferenceService=null;

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public void setConferenceService(IConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    public String addConference(){
        conferenceService.addConference(conference);
        return "success";
    }


}
