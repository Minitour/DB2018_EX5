package controller;

import com.google.gson.JsonObject;
import database.data_access.PersonAccess;
import model.Person;
import model.Session;
import spark.Request;
import spark.Response;
import utils.JSONResponse;
import utils.RESTRoute;

import java.util.List;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class ProfileController implements RESTRoute {

    @Override
    public Object handle(Request request, Response response, JsonObject body, Session session) throws Exception {

        //require session token
        require(session);

        try(PersonAccess person_db = new PersonAccess()){

            //validate session token
            validateSession(session,person_db,false);


            //check permissions
            if(!hasPermission("io.hospital.profile.read",session))
                return JSONResponse.FAILURE().message("Access Denied.");


            List<Person> personList = person_db.getById(session.ACCOUNT_ID,null);

            if(personList.size() != 1)
                return JSONResponse.FAILURE().message("Profile not found.");

            Person profile = personList.get(0);

            return JSONResponse.SUCCESS().data(profile);
        }catch (Exception e){
            return JSONResponse.FAILURE().message(e.getMessage());
        }

    }
}
