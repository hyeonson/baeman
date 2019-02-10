package project.hs.baeman.Item;

public class MenuItem {
    private int menuNumber;
    private String menuName;
    private int price;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getMenuNumber() {
        return menuNumber;
    }

    public void setMenuNumber(int menuNumber) {
        this.menuNumber = menuNumber;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MenuItem(int menuNumber, String menuName, int price, String imgUrl) {
        this.menuNumber = menuNumber;
        this.menuName = menuName;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
