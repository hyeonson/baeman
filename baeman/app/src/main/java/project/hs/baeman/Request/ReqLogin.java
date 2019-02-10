package project.hs.baeman.Request;

public class ReqLogin {
    String userEmailId;
    String userPw;
    public ReqLogin(String userEmailId, String userPw) {
        this.userEmailId = userEmailId;
        this.userPw = userPw;
    }
    /*
    public String getUserEmailId() {
        return userEmailId;
    }
    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }
    public String getUserPw() {
        return userPw;
    }
    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
    */
}
