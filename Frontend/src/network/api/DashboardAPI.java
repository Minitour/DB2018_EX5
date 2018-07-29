package network.api;

import com.google.gson.JsonObject;
import javafx.application.Platform;
import network.SharedInstance;
import utils.AutoSignIn;
import utils.Constants;
import utils.Response;

/**
 * Created By Tony on 29/07/2018
 */
public class DashboardAPI {

    public void getData(DashboardCallback callback){

        JsonObject data = new JsonObject();

        data.addProperty("ACCOUNT_ID", AutoSignIn.ID);
        data.addProperty("SESSION_TOKEN",AutoSignIn.SESSION_TOKEN);


        SharedInstance.main.makeRequest(Constants.Routes.dashboard(), null, data, (json, exception) -> {
            Response r = new Response(json);
            r.setException(exception);

            Platform.runLater(()-> {
                if(exception == null && r.isOK()){
                    callback.make(r,json);
                }else
                    callback.make(r,null);
            });

        });
    }

    public interface DashboardCallback {
        void make(Response response,JsonObject data);
    }
}
