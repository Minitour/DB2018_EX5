package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.MedicalEventAccess;
import database.data_access.MedicalEventTypesAccess;
import database.data_access.PaymentAccess;
import model.MedicalEvent;
import model.MedicalEventTypes;
import model.Payment;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

/**
 * Created by Antonio Zaitoun on 30/07/2018.
 */
public class MedicalEventTypesController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        int id = params.get("typeCode").getAsInt();

        try(MedicalEventTypesAccess access = new MedicalEventTypesAccess()) {

            validateSession(session, access, false);

            if (!hasPermission("io.hospital.event_type.read", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<MedicalEventTypes> payments = access.getById(id);
            if (payments.size() != 1)
                return JSONResponse.FAILURE().message("Not found.");

            return JSONResponse
                    .SUCCESS()
                    .data(payments.get(0));

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

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
        JsonObject params = parameters(body);
        require(params);

        int serialNumber = params.get("typeCode").getAsInt();

        try(MedicalEventTypesAccess access = new MedicalEventTypesAccess()) {

            validateSession(session, access, false);

            if (!hasPermission("io.hospital.event_type.delete", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            access.delete(serialNumber);

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

        MedicalEventTypes eventType = gson.fromJson(params,MedicalEventTypes.class);

        try(MedicalEventTypesAccess access= new MedicalEventTypesAccess()) {

            //validate session
            validateSession(session,access,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.event_type.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            access.upsert(eventType);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }
}
