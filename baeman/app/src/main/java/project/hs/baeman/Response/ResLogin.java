package project.hs.baeman.Response;

import project.hs.baeman.Data.User;

public class ResLogin {
    private int code;
    private boolean isSuccess;
    private User data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
