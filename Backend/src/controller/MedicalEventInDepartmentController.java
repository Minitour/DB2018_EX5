package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.MedicalEventTypeInDepartmentAccess;
import model.MedicalEventTypeInDepartment;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class MedicalEventInDepartmentController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        int hospitalID = params.get("hospitalID").getAsInt();
        int departmentID = params.get("departmentID").getAsInt();

        try(MedicalEventTypeInDepartmentAccess medicalEventTypeInDepartmentAccess = new MedicalEventTypeInDepartmentAccess()) {

            validateSession(session, medicalEventTypeInDepartmentAccess, false);

            if (!hasPermission("io.hospital.event_in_dep.read", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<MedicalEventTypeInDepartment> medicals = medicalEventTypeInDepartmentAccess.getById(hospitalID, departmentID);
            JSONResponse<List<MedicalEventTypeInDepartment>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(medicals);

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

        try(MedicalEventTypeInDepartmentAccess medicalEventTypeInDepartmentAccess = new MedicalEventTypeInDepartmentAccess()) {

            validateSession(session, medicalEventTypeInDepartmentAccess, false);

            if (!hasPermission("io.hospital.event_in_dep.read_all", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<MedicalEventTypeInDepartment> medicals = medicalEventTypeInDepartmentAccess.getById(hospitalID);
            JSONResponse<List<MedicalEventTypeInDepartment>> jsonResponse = JSONResponse.SUCCESS();
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

        MedicalEventTypeInDepartment medicalEventTypeInDepartment = gson.fromJson(params.get("eventInDep"),MedicalEventTypeInDepartment.class);

        try(MedicalEventTypeInDepartmentAccess medicalEventTypeInDepartment_db = new MedicalEventTypeInDepartmentAccess()) {

            //validate session
            validateSession(session,medicalEventTypeInDepartment_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.event_in_dep.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            medicalEventTypeInDepartment_db.upsert(medicalEventTypeInDepartment);

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

        int hospitalID = params.get("hospitalID").getAsInt();
        int departmentID = params.get("departmentID").getAsInt();
        int eventCode = params.get("eventCode").getAsInt();

        try(MedicalEventTypeInDepartmentAccess medicalEventTypeInDepartmentAccess = new MedicalEventTypeInDepartmentAccess()) {

            validateSession(session, medicalEventTypeInDepartmentAccess, false);

            if (!hasPermission("io.hospital.event_in_dep.delete", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            medicalEventTypeInDepartmentAccess.delete(hospitalID, departmentID, eventCode);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }
}
