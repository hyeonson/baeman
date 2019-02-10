package project.hs.baeman.Item;

public class InfoItem {
    private String infoName;
    private String infoContent;

    public InfoItem(String infoName, String infoContent) {
        this.infoName = infoName;
        this.infoContent = infoContent;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }
}
