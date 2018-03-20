package blog.model;

public class History {

    private int id;
    private String content;
    private boolean isBig;
    private String version;
    private String date;

    public History() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsBig() {
        return isBig;
    }

    public void setIsBig(boolean isBig) {
        this.isBig = isBig;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

}
