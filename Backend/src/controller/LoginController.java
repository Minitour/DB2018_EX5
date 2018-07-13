package controller;

import com.google.gson.JsonObject;
import model.Session;
import spark.Request;
import spark.Response;
import utils.RESTRoute;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class LoginController implements RESTRoute {

    @Override
    public Object handle(Request request, Response response, JsonObject body, Session session) throws Exception {

        return null;
    }
}
