package network.api;

import com.google.gson.JsonObject;
import javafx.application.Platform;
import utils.AutoSignIn;
import utils.Constants;
import utils.Response;
import network.SharedInstance;

/**
 * Created By Tony on 23/07/2018
 */
public class AuthAPI {

    /**
     * Login with email and password.
     *
     * @param email The email of the account.
     * @param password The password of the account.
     * @param callback The response callback.
     */
    public void login(String email, String password, Auth callback){
        JsonObject data = new JsonObject();

        data.addProperty("EMAIL",email);
        data.addProperty("PASSWORD",password);

        SharedInstance.main.makeRequest(Constants.Routes.login(), null, data, (json, exception) -> {
            Response r = new Response(json);
            r.setException(exception);

            Platform.runLater(()-> {
                if(exception == null && r.isOK()){
                    JsonObject d = json.get("data").getAsJsonObject();

                    int id = d.get("ACCOUNT_ID").getAsInt();
                    String token = d.get("SESSION_TOKEN").getAsString();
                    int hospital = d.get("hospitalId").getAsInt();
                    int roleId = d.get("role").getAsInt();

                    AutoSignIn.ID = id;
                    AutoSignIn.SESSION_TOKEN = token;
                    AutoSignIn.ROLE_ID = roleId;
                    AutoSignIn.EMAIL = email;
                    AutoSignIn.HOSPITAL_ID = hospital;

                    callback.make(r,id,token,roleId);
                }else
                    callback.make(r,null,null,-1);
            });

        });

    }

    public void updatePassword(String currentPassword,String newPassword,UpdatePassword callback){

        JsonObject data = new JsonObject();

        data.addProperty("ACCOUNT_ID",AutoSignIn.ID);
        data.addProperty("SESSION_TOKEN",AutoSignIn.SESSION_TOKEN);
        data.addProperty("currentPassword",currentPassword);
        data.addProperty("newPassword",newPassword);


        SharedInstance.main.makeRequest(Constants.Routes.login(), null, data, (json, exception) -> {
            Response r = new Response(json);
            r.setException(exception);

            Platform.runLater(()-> {
                if(exception == null && r.isOK()){
                    callback.make(r);
                }else
                    callback.make(r);
            });

        });
    }

    @FunctionalInterface
    public interface Auth {
        void make(Response response, Integer id, String token, int roleId);
    }

    @FunctionalInterface
    public interface UpdatePassword {
        void make(Response response);
    }
}
