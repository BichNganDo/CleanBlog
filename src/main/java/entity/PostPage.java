package entity;

public class PostPage {

    private int id;
    private String main_title;
    private String sub_title;
    private String content;
    private String image;
    private String post_date;
    private String status;
    private String author;

    public PostPage() {
    }

    public PostPage(int id, String main_title, String sub_title, String content, String image, String post_date, String status, String author) {
        this.id = id;
        this.main_title = main_title;
        this.sub_title = sub_title;
        this.content = content;
        this.image = image;
        this.post_date = post_date;
        this.status = status;
        this.author = author;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
