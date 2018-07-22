package network;

import com.google.gson.JsonObject;

/**
 * Created By Tony on 14/02/2018
 */
public class Response {

    private int code;
    private String message;
    private Exception exception;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    void setCode(int code) {
        this.code = code;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(JsonObject json){
        try {
            code = json.get("code").getAsInt();
            message = json.get("message").getAsString();
        }catch (NullPointerException e){
            code = -1;
            message = "Unknown Error";
        }
    }

    public boolean isOK(){
        return getCode() == Constants.Codes.SUCCESS;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
