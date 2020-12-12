package Service;

import Po.User;

public class UserService {
    public boolean login(User user){
        if(user.getUpassword().equals(user.getUid())){
            return true;
        }
        return false;
    }
}
