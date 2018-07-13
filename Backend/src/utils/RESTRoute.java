package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Session;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Antonio Zaitoun on 09/02/2018.
 */
@FunctionalInterface
public interface RESTRoute extends Route {

    @Override
    default Object handle(Request request, Response response) throws Exception{
        response.header("Content-Type","application/json");
        return handle(request,response,new Gson().fromJson(request.body(), JsonObject.class));
    }

    Object handle(Request request, Response response, JsonObject body) throws Exception;

    default Session extractFromBody(JsonObject body){
        try {
            String id = body.get("ACCOUNT_ID").getAsString();
            String sessionToken = body.get("SESSION_TOKEN").getAsString();
            return new Session(id,sessionToken);
        }catch (NullPointerException e){
            return null;
        }
    }
}
