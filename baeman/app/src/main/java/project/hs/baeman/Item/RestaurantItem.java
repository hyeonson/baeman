package project.hs.baeman.Item;

public class RestaurantItem {
    private int number;
    private String restaurant;
    private String menu;
    private String imgUrl;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RestaurantItem(int number, String restaurant, String menu, String imgUrl, int status) {
        this.number = number;
        this.restaurant = restaurant;
        this.menu = menu;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
