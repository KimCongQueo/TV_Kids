package quanth.vn.com.tv_kids.models;

public class Image {
    public String url;
    public float width;
    public float height;

    public Image() {
    }

    public Image(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }
}
