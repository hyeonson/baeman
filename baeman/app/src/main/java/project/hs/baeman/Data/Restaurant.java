package project.hs.baeman.Data;

public class Restaurant {
    private int restaurantNumber;
    private String restaurantName;
    private String mainMenu;
    private int status;

    public int getRestaurantNumber() {
        return restaurantNumber;
    }

    public void setRestaurantNumber(int restaurantNumber) {
        this.restaurantNumber = restaurantNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(String mainMenu) {
        this.mainMenu = mainMenu;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
