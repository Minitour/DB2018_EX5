package utils;

import com.google.gson.JsonObject;
import model.Session;
import spark.Request;
import spark.Response;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class GenericController implements RESTRoute {
    @Override
    public Object handle(Request request, Response response, JsonObject body, Session session) throws Exception {
        String action = null;

        try {
             action = body.get("action").getAsString();
        }catch (NullPointerException ignored) {}

        switch (action){
            case "delete":
                return delete(body,session);
            case "read_all":
                return readAll(body,session);
            case "read":
                return read(body,session);
            case "upsert":
                return upsert(body,session);
            default:
                return JSONResponse.FAILURE().message("Unknown Action");
        }
    }

    public Object read(JsonObject body, Session session){ return JSONResponse.FAILURE(); }

    public Object readAll(JsonObject body, Session session){ return JSONResponse.FAILURE(); }

    public Object delete(JsonObject body, Session session){ return JSONResponse.FAILURE(); }

    public Object upsert(JsonObject body, Session session){ return JSONResponse.FAILURE(); }
}
