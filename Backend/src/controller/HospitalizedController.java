package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.HospitalizedAccess;
import model.Hospitalized;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class HospitalizedController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        String patientID = params.get("patientID").getAsString();
        int eventCode = params.get("eventCode").getAsInt();
        int hospitalID = params.get("hospitalID").getAsInt();
        int departmentID = params.get("departmentID").getAsInt();
        int roomNumber = params.get("roomNumber").getAsInt();

        try(HospitalizedAccess hospitalizedAccess = new HospitalizedAccess()) {

            validateSession(session, hospitalizedAccess, false);

            if (!hasPermission("io.hospital.hospitalized.read", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<Hospitalized> hospitalized = hospitalizedAccess.getById(patientID, eventCode, hospitalID, departmentID, roomNumber);
            JSONResponse<List<Hospitalized>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(hospitalized);

            return jsonResponse;

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        int hospitalID = params.get("hospitalID").getAsInt();
        int departmentID = params.get("departmentID").getAsInt();


        try(HospitalizedAccess hospitalizedAccess = new HospitalizedAccess()) {

            validateSession(session, hospitalizedAccess, false);

            if (!hasPermission("io.hospital.hospitalized.read_all", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<Hospitalized> hospitalized = hospitalizedAccess.getAll(hospitalID, departmentID);
            JSONResponse<List<Hospitalized>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(hospitalized);

            return jsonResponse;

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

        Hospitalized hospitalized = gson.fromJson(params.get("hospitalized"),Hospitalized.class);

        try(HospitalizedAccess hospitalized_db = new HospitalizedAccess()) {

            //validate session
            validateSession(session,hospitalized_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.hospitalized.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            hospitalized_db.upsert(hospitalized);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }

    @Override
    public Object delete(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        String patientID = params.get("patientID").getAsString();
        int eventCode = params.get("eventCode").getAsInt();
        int hospitalID = params.get("hospitalID").getAsInt();
        int departmentID = params.get("departmentID").getAsInt();
        int roomNumber = params.get("roomNumber").getAsInt();

        try(HospitalizedAccess hospitalizedAccess = new HospitalizedAccess()) {

            validateSession(session, hospitalizedAccess, false);

            if (!hasPermission("io.hospital.hospitalized.delete", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            hospitalizedAccess.delete(patientID, eventCode, hospitalID, departmentID, roomNumber);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }
}
