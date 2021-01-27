package Action;

import Po.Reserve;
import Service.IConferenceService;
import Service.IReserveService;
import Service.IUserService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import processor.JsonDateValueProcessor;
import processor.JsonTimeValueProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.tools.Tool;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class ZPJsonAction {
    private IUserService userService = null;
    private IReserveService reserveService = null;
    private IConferenceService conferenceService = null;
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

    //返回所需要格式的json数据
    public String zpjsonUserList() {
        List userlist = userService.userList(page, limit);
        data = new JSONObject();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", userService.userCount());
        data.put("data", userlist);
        return "success";
    }

    public String zpjsonReserveList() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        List reservelist = reserveService.InfoList(page, limit);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", reserveService.Count());
        hashMap.put("data", reservelist);
        data = new JSONObject();
        data.putAll(hashMap, jsonConfig);
        return "success";
    }

    public String zpjsongraReserveList() {
        HashMap<String, Object> hashMaptmp = new HashMap<String, Object>();
        List tmp = Arrays.asList(new String[]{"Name", "Type", "Near Bridge"});
        List tmp2 = Arrays.asList(new String[]{"R01", "5", "true"}, new String[]{"R02", "10", "true"}, new String[]{"R03", "20", "true"}, new String[]{"R04", "15", "true"});
        hashMaptmp.put("dimensions", tmp);
        hashMaptmp.put("data", tmp2);

        HashMap<String, Object> hashMaptmp2 = new HashMap<String, Object>();
        List tmp3 = Arrays.asList(new String[]{"Room Id", "Starting Time", "Ending Time"});

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        String hql = "from Reserve r where r.state = '1'";
        List result = reserveService.findAll(hql);
        List tmp4 = new ArrayList();
        for (int i = 0; i < result.size(); ++i) {
            String rid = result.get(i).toString().substring(4, 7);
            String stmp = result.get(i).toString().substring(13, 23) + " " + result.get(i).toString().substring(34, 42);
            java.util.Calendar cal1 = java.util.Calendar.getInstance();
            cal1.set(Integer.parseInt(stmp.substring(0, 4)),
                    Integer.parseInt(stmp.substring(5, 7)) - 1,
                    Integer.parseInt(stmp.substring(8, 10)),
                    Integer.parseInt(stmp.substring(11, 13)),
                    Integer.parseInt(stmp.substring(14, 16)),
                    Integer.parseInt(stmp.substring(17, 19)));
            cal1.set(java.util.Calendar.MILLISECOND, 0);
            Long s = cal1.getTime().getTime();
            String etmp = result.get(i).toString().substring(13, 23) + " " + result.get(i).toString().substring(51, 59);
            java.util.Calendar cal2 = java.util.Calendar.getInstance();
            cal2.set(Integer.parseInt(etmp.substring(0, 4)),
                    Integer.parseInt(etmp.substring(5, 7)) - 1,
                    Integer.parseInt(etmp.substring(8, 10)),
                    Integer.parseInt(etmp.substring(11, 13)),
                    Integer.parseInt(etmp.substring(14, 16)),
                    Integer.parseInt(etmp.substring(17, 19)));
            cal2.set(java.util.Calendar.MILLISECOND, 0);
            Long e = cal2.getTime().getTime();

            if (rid.equals("R01")) {
                List tmpp = new ArrayList();
                tmpp.add(0);
                tmpp.add(s);
                tmpp.add(e);
                tmpp.add("R01");
                tmp4.add(tmpp);
            } else if (rid.equals("R02")) {
                List tmpp = new ArrayList();
                tmpp.add(1);
                tmpp.add(s);
                tmpp.add(e);
                tmpp.add("R02");
                tmp4.add(tmpp);
            } else if (rid.equals("R03")) {
                List tmpp = new ArrayList();
                tmpp.add(2);
                tmpp.add(s);
                tmpp.add(e);
                tmpp.add("R03");
                tmp4.add(tmpp);
            } else if (rid.equals("R04")) {
                List tmpp = new ArrayList();
                tmpp.add(3);
                tmpp.add(s);
                tmpp.add(e);
                tmpp.add("R04");
                tmp4.add(tmpp);
            }
        }
        hashMaptmp2.put("dimensions", tmp3);
        hashMaptmp2.put("data", tmp4);
        data = new JSONObject();
        data.put("parkingApron", hashMaptmp);
        data.put("flight", hashMaptmp2);
        return "success";
    }

    public String zpjsonHistory() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        List reservelist = reserveService.history(page, limit);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", reserveService.historyCount());
        hashMap.put("data", reservelist);
        data = new JSONObject();
        data.putAll(hashMap, jsonConfig);
        return "success";
    }

    public String zpjsonMyReserveList() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        List reservelist = reserveService.InfoMyList(page, limit);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", reserveService.CountMy());
        hashMap.put("data", reservelist);
        data = new JSONObject();
        data.putAll(hashMap, jsonConfig);
        return "success";
    }

    public String zpjsonConferenceList() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        List list = conferenceService.InfoList(page, limit);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", conferenceService.Count());
        hashMap.put("data", list);
        data = new JSONObject();
        data.putAll(hashMap, jsonConfig);
        return "success";
    }

    public String zpjsonParticipantList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String reid = request.getParameter("reid");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Time.class, new JsonTimeValueProcessor());
        List list = conferenceService.InfoListPa(reid, page, limit);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", conferenceService.CountPa(reid));
        hashMap.put("data", list);
        data = new JSONObject();
        data.putAll(hashMap, jsonConfig);
        return "success";
    }

    public String zpjsonRoomList() {
        List roomlist = userService.roomList(page, limit);
        data = new JSONObject();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", userService.roomCount());
        data.put("data", roomlist);
        return "success";
    }
}