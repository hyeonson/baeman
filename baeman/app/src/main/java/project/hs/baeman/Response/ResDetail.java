package project.hs.baeman.Response;

import project.hs.baeman.Data.RestaurantDetail;

public class ResDetail {
    private int code;
    private boolean isSuccess;
    private String message;
    private RestaurantDetail data;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestaurantDetail getData() {
        return data;
    }

    public void setData(RestaurantDetail data) {
        this.data = data;
    }
}
