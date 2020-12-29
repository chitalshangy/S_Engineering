package Action;

import Service.IUserService;
import Service.UserServiceImpl;
import net.sf.json.JSONObject;

import java.util.List;

public class ZPJsonAction {
    private IUserService userService;
    JSONObject data;
    private int page;
    private int limit;

    public void setUserService(UserServiceImpl userService) {
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

    public String zpjsonUserList() {
        List userlist = userService.InfoList(page, limit);
        data = new JSONObject();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", userService.Count());
        data.put("data", userlist);
        return "success";
    }
}
