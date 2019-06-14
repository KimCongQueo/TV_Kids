package quanth.vn.com.tv_kids.models;

public class Video {
    int id;
    String videoId;
    Image image = new Image();
    String title;
    String caption;
    Boolean isLike = false;
    Boolean isBookmark = false;
    public transient VideoBinder binder = new VideoBinder(this);

    public Video(String videoId, Image image, String title, String caption) {
        this.videoId = videoId;
        this.image = image;
        this.title = title;
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getBookmark() {
        return isBookmark;
    }

    public void setBookmark(Boolean bookmark) {
        isBookmark = bookmark;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Image getImage() {
        return image;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
