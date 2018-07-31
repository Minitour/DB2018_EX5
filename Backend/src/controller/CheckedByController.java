package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.CheckedByAccess;
import model.CheckedBy;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.sql.Date;
import java.util.List;

public class CheckedByController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        String patientID = params.get("patientID").getAsString();
        int eventCode = params.get("eventCode").getAsInt();
        String doctorID = params.get("doctorID").getAsString();
        int shiftNumber = params.get("shiftNumber").getAsInt();
        Date checkTime = new Date(params.get("checkTime").getAsLong());

        try(CheckedByAccess checkedByAccess = new CheckedByAccess()) {

            validateSession(session, checkedByAccess, false);

            if (!hasPermission("io.hospital.check.read", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<CheckedBy> checks = checkedByAccess.getById(patientID, eventCode, doctorID, shiftNumber, checkTime);
            JSONResponse<List<CheckedBy>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(checks);

            return jsonResponse;

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        try(CheckedByAccess checkedByAccess = new CheckedByAccess()) {

            validateSession(session, checkedByAccess, false);

            if (!hasPermission("io.hospital.check.read_all", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<CheckedBy> checks = checkedByAccess.getAll();
            JSONResponse<List<CheckedBy>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(checks);

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

        CheckedBy checkedBy = gson.fromJson(params,CheckedBy.class);

        try(CheckedByAccess checkedBy_db = new CheckedByAccess()) {

            //validate session
            validateSession(session,checkedBy_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.check.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            checkedBy_db.upsert(checkedBy);

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

//        String patientID = params.get("patientID").getAsString();
//        int eventCode = params.get("eventCode").getAsInt();
//        String doctorID = params.get("doctorID").getAsString();
//        int shiftNumber = params.get("shiftNumber").getAsInt();
//        Date checkTime = new Date(params.get("checkTime").getAsLong());

        Gson gson = new Gson();
        CheckedBy checkedBy =  gson.fromJson(params,CheckedBy.class);

        try(CheckedByAccess checkedByAccess = new CheckedByAccess()) {

            validateSession(session, checkedByAccess, false);

            if (!hasPermission("io.hospital.check.delete", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            checkedByAccess.delete(checkedBy.getPatientID(), checkedBy.getEventCode(), checkedBy.getDoctorID(), checkedBy.getShiftNumber(), checkedBy.getCheckTime());

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }
}
