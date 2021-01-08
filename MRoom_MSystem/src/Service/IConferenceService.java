package Service;

import Po.Conference;

import java.io.IOException;
import java.util.List;

public interface IConferenceService {
    public void addConference(Conference conference);

    public List InfoList(int page, int limit);

    public long Count();

    public void deleteConference(String reid);

    public void deleteConferenceByManager(String uid);

    public List InfoListPa(String reid, int page, int limit);

    public long CountPa(String reid);

    public Boolean checkIn(String a,String rid) throws IOException;
}
