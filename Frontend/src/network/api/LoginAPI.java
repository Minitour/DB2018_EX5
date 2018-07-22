package network.api;

import com.google.gson.JsonObject;
import network.AutoSignIn;
import network.Constants;
import network.Response;
import network.generic.SharedInstance;

/**
 * Created By Tony on 23/07/2018
 */
public class LoginAPI {

    public void login(String email, String password, Auth callback){
        JsonObject data = new JsonObject();

        data.addProperty("EMAIL",email);
        data.addProperty("PASSWORD",password);

        SharedInstance.main.makeRequest(Constants.Routes.login(), null, data, (json, exception) -> {
            Response r = new Response(json);
            r.setException(exception);

            if(exception == null && r.isOK()){
                JsonObject d = json.get("data").getAsJsonObject();

                int id = d.get("ACCOUNT_ID").getAsInt();
                String token = d.get("SESSION_TOKEN").getAsString();
                int roleId = d.get("role").getAsInt();

                AutoSignIn.ID = id;
                AutoSignIn.SESSION_TOKEN = token;
                AutoSignIn.ROLE_ID = roleId;
                AutoSignIn.EMAIL = email;

                callback.make(r,id,token,roleId);
            }else
                callback.make(r,null,null,-1);
        });

    }

    @FunctionalInterface
    public interface Auth {
        void make(Response response, Integer id, String token, int roleId);
    }
}
