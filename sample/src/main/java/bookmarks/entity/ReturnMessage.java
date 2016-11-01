package bookmarks.entity;

/**
 * Created by Larry on 2016/11/1.
 */
public class ReturnMessage {

    private Integer flag;
    private String message;

    public ReturnMessage(Integer flag, String message){
        this.flag = flag;
        this.message = message;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
