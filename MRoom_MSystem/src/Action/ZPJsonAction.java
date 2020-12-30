package Action;

import Service.IConferenceService;
import Service.IReserveService;
import Service.IUserService;
import Service.UserService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import processor.JsonDateValueProcessor;
import processor.JsonTimeValueProcessor;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

public class ZPJsonAction {
    private IUserService userService=null;
    private IReserveService reserveService=null;
    private IConferenceService conferenceService=null;
    JSONObject data;
    private int page;
    private int limit;

    public void setConferenceService(IConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    public void setReserveService(IReserveService reserveService) {
        this.reserveService = reserveService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String zpjsonUserList(){
        List userlist=userService.InfoList(page,limit);
        data=new JSONObject();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", userService.Count());
        data.put("data", userlist);
        return "success";
    }

    public String zpjsonReserveList(){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        List reservelist= reserveService.InfoList(page,limit);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", reserveService.Count());
        hashMap.put("data", reservelist);
        data=new JSONObject();
        data.putAll(hashMap,jsonConfig);
        return "success";
    }

    public String zpjsonMyReserveList(){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        List reservelist= reserveService.InfoMyList(page,limit);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", reserveService.CountMy());
        hashMap.put("data", reservelist);
        data=new JSONObject();
        data.putAll(hashMap,jsonConfig);
        return "success";
    }

    public String zpjsonConferenceList(){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        List list= conferenceService.InfoList(page,limit);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", conferenceService.Count());
        hashMap.put("data", list);
        data=new JSONObject();
        data.putAll(hashMap,jsonConfig);
        return "success";
    }

}
