package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.PatientAccess;
import database.data_access.PersonAccess;
import model.Person;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class PatientsController extends GenericController{

    @Override
    public Object read(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        String id = params.get("ID").getAsString();

        try(PatientAccess access = new PatientAccess()){

            //validate session.
            validateSession(session,access,false);

            //validate permissions.
            if(!hasPermission("io.hospital.patient.read",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<Person> people = access.getById(id);

            if(people.size() != 1)
                return JSONResponse.FAILURE().message("Not found.");

            Person p = people.get(0);

            return JSONResponse.SUCCESS().data(p);

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        try(PatientAccess patient_db = new PatientAccess()){

            //check session
            validateSession(session,patient_db,false);

            //check permission
            if(!hasPermission("io.hospital.patient.read_all",session))
                return JSONResponse.FAILURE().message("Access denied.");

            //read all
            List<Person> people = patient_db.getAll();
            JSONResponse<List<Person>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(people);

            return jsonResponse;
        }catch (Exception e){
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }

    @Override
    public Object delete(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        String id = params.get("ID").getAsString();

        try(PatientAccess access = new PatientAccess()){

            //validate session.
            validateSession(session,access,false);

            //validate permissions.
            if(!hasPermission("io.hospital.patient.delete",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            access.delete(id);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object upsert(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);
        Gson gson = new Gson();

        Person p = gson.fromJson(params,Person.class);

        try(PatientAccess patient_db = new PatientAccess();
            PersonAccess person_db = new PersonAccess(patient_db)) {

            //validate session
            validateSession(session,person_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.profile.upsert",session)
                    && hasPermission("io.hospital.patient.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            person_db.upsert(p);
            patient_db.upsert(p.getID());

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }
}
