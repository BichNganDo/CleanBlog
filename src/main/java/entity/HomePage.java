package entity;

public class HomePage {

    private int id;
    private String main_title;
    private String sub_title;
    private String image;

    public HomePage() {
    }

    public HomePage(int id, String main_title, String sub_title, String image) {
        this.id = id;
        this.main_title = main_title;
        this.sub_title = sub_title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
