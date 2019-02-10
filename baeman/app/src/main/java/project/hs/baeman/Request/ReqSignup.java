package project.hs.baeman.Request;

public class ReqSignup {
    String userPhoneNumber;
    String userEmailId;
    String userPw;
    String userNickname;

    public ReqSignup(String userPhoneNumber, String userEmailId, String userPw, String userNickname) {
        this.userPhoneNumber = userPhoneNumber;
        this.userEmailId = userEmailId;
        this.userPw = userPw;
        this.userNickname = userNickname;
    }
}
