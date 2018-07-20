package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Session;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Arrays;

/**
 * Created by Antonio Zaitoun on 09/02/2018.
 */
@FunctionalInterface
public interface RESTRoute extends Route {

    @Override
    default Object handle(Request request, Response response) throws Exception{
        try {
            response.header("Content-Type", "application/json");
            JsonObject o = new Gson().fromJson(request.body(), JsonObject.class);

            return handle(request, response, o, extractFromBody(o));
        }catch (Exception e){
            return JSONResponse.FAILURE().message(e.getMessage());
        }
    }

    Object handle(Request request, Response response, JsonObject body, Session session) throws Exception;

    default Session extractFromBody(JsonObject body){
        try {
            int id = body.get("ACCOUNT_ID").getAsInt();
            String sessionToken = body.get("SESSION_TOKEN").getAsString();
            return new Session(id,sessionToken);
        }catch (NullPointerException e){
            return null;
        }
    }

    default void require(Object... values){
        for (Object value : values)
            if(value == null)
                throw new RuntimeException("Missing Parameter!");
    }

    default boolean requireNotZero(Number... numbers){
        for (Number number : numbers) {
            if(number.equals(0))
                return false;
        }
        return true;
    }
}
