package Service;

import Po.User;

public class UserService {
    public boolean login(User user){
        if(user.getPassword().equals(user.getUser_id())){
            return true;
        }
        return false;
    }
}
