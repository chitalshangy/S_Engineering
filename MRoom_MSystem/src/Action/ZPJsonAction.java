package Action;

import Service.IUserService;
import Service.UserServiceImpl;
import net.sf.json.JSONObject;

import java.util.List;

public class ZPJsonAction {
    private IUserService userService;

    //返回值
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
        List userlist = userService.userList(page, limit);
        data = new JSONObject();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", userService.userCount());
        data.put("data", userlist);
        return "success";
    }

    public String zpjsonRoomList(){
        List roomlist=userService.roomList(page, limit);
        data=new JSONObject();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", userService.roomCount());
        data.put("data", roomlist);
        return "success";
    }
}
