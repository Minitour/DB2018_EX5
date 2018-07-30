package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.DoctorAccess;
import database.data_access.PatientAccess;
import database.data_access.PersonAccess;
import model.Person;
import model.Session;
import spark.Request;
import spark.Response;
import utils.GenericController;
import utils.JSONResponse;
import utils.RESTRoute;

import java.util.List;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class ProfileController extends GenericController {


    @Override
    public Object read(JsonObject body, Session session) {

        //require session token
        require(session);

        try(PersonAccess personAccess = new PersonAccess()){

            //validate session token
            validateSession(session,personAccess,false);

            //check permissions
            if(!hasPermission("io.hospital.profile.read",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<Person> personList = personAccess.getById(session.ACCOUNT_ID,null);

            if(personList.size() != 1)
                return JSONResponse.FAILURE().message("Profile not found.");

            Person profile = personList.get(0);

            return JSONResponse.SUCCESS().data(profile);

        }catch (Exception e){
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        try(PersonAccess personAccess = new PersonAccess()){

            //validate session token
            validateSession(session,personAccess,false);

            //check permissions
            if(!hasPermission("io.hospital.profile.read_all",session)) {
                //can read one
                if (!hasPermission("io.hospital.profile.read",session)){
                    return JSONResponse.FAILURE().message("Access Denied.");
                }else {
                    List<Person> personList = personAccess.getAll();
                    personList.removeIf(person -> session.ACCOUNT_ID.equals(person.getACCOUNT_ID()));
                    JSONResponse<List<Person>> jsonResponse = JSONResponse.SUCCESS();
                    jsonResponse.data(personList);
                    return jsonResponse;
                }
            }


            List<Person> personList = personAccess.getAll();
            JSONResponse<List<Person>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(personList);

            return jsonResponse;

        }catch (Exception e){
            return JSONResponse.FAILURE().message(e.getMessage());
        }
    }

    @Override
    public Object upsert(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);
        Gson gson = new Gson();

        Person person = gson.fromJson(params,Person.class);

        try(PersonAccess person_db = new PersonAccess()) {

            //validate session
            validateSession(session,person_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.profile.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            person_db.upsert(person);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }

    @Override
    public Object delete(JsonObject body, Session session) {
        //require session token
        require(session);

        try(PersonAccess personAccess = new PersonAccess()){

            //validate session token
            validateSession(session,personAccess,false);

            //check permissions
            if(!hasPermission("io.hospital.profile.delete",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            personAccess.delete(session.ACCOUNT_ID);

            return JSONResponse.SUCCESS();

        }catch (Exception e){
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }
}
