package project.hs.baeman;

import android.app.Application;

public class BaemanApplication extends Application {
    private String userEmailId;
    private String userNickname;
    private String jwt;

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
