package controller;

import com.google.gson.JsonObject;
import database.data_access.MedicalEventAccess;
import database.data_access.MedicalEventTypesAccess;
import model.MedicalEvent;
import model.MedicalEventTypes;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

/**
 * Created by Antonio Zaitoun on 30/07/2018.
 */
public class MedicalEventTypesController extends GenericController {


    @Override
    public Object readAll(JsonObject body, Session session) {
        try(MedicalEventTypesAccess medicalEventAccess = new MedicalEventTypesAccess()) {

            validateSession(session, medicalEventAccess, false);

            if (!hasPermission("io.hospital.event_type.read_all", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<MedicalEventTypes> medicals = medicalEventAccess.getAll();
            JSONResponse<List<MedicalEventTypes>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(medicals);

            return jsonResponse;

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object delete(JsonObject body, Session session) {
        return super.delete(body, session);
    }
}
