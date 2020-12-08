package Po;

import java.awt.*;

public class User {
    private String user_id;
    private String password;
    private Image image;

    public User() {
    }

    public User(String user_id, String password, Image image) {
        this.user_id = user_id;
        this.password = password;
        this.image = image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
