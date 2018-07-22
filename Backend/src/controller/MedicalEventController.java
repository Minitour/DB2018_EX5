package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.MedicalEventAccess;
import model.MedicalEvent;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class MedicalEventController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        int eventCode = params.get("eventCode").getAsInt();

        try(MedicalEventAccess medicalEventAccess = new MedicalEventAccess()) {

            validateSession(session, medicalEventAccess, false);

            if (!hasPermission("io.hospital.event.read", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<MedicalEvent> medicals = medicalEventAccess.getById(eventCode);

            if (medicals.size() != 1)
                return JSONResponse.FAILURE().message("Medical Event Not Found.");

            MedicalEvent m = medicals.get(0);

            return JSONResponse.SUCCESS().data(m);

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        try(MedicalEventAccess medicalEventAccess = new MedicalEventAccess()) {

            validateSession(session, medicalEventAccess, false);

            if (!hasPermission("io.hospital.event.read_all", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<MedicalEvent> medicals = medicalEventAccess.getAll();
            JSONResponse<List<MedicalEvent>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(medicals);

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

        MedicalEvent medicalEvent = gson.fromJson(params.get("medicalEvent"),MedicalEvent.class);

        try(MedicalEventAccess medicalEvent_db = new MedicalEventAccess()) {

            //validate session
            validateSession(session,medicalEvent_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.event.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            medicalEvent_db.upsert(medicalEvent);

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

        int eventCode = params.get("eventCode").getAsInt();

        try(MedicalEventAccess medicalEventAccess = new MedicalEventAccess()) {

            validateSession(session, medicalEventAccess, false);

            if (!hasPermission("io.hospital.event.delete", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            medicalEventAccess.delete(eventCode);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }


    }
}
