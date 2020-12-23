package Service;

import Po.Conference;

import java.util.List;

public interface IConferenceService {
    public void addConference(Conference conference);
    public List InfoList(int page, int limit);
    public long Count();
}
