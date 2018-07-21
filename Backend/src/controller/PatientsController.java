package controller;

import com.google.gson.JsonObject;
import database.data_access.PersonAccess;
import model.Person;
import model.Session;
import spark.Request;
import spark.Response;
import utils.GenericController;
import utils.RESTRoute;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class PatientsController extends GenericController{

    @Override
    public Object read(JsonObject body, Session session) {
        return null;
    }
}
