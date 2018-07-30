package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.DoctorVacationAccess;
import model.Doctor;
import model.DoctorVacation;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class DoctorVacationsController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        String doctorID = params.get("doctorID").getAsString();

        try(DoctorVacationAccess doctorVacationAccess = new DoctorVacationAccess()) {

            validateSession(session, doctorVacationAccess, false);

            if (!hasPermission("io.hospital.vacation.read", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<DoctorVacation> doctorVacations = doctorVacationAccess.getById(doctorID);
            JSONResponse<List<DoctorVacation>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(doctorVacations);

            return jsonResponse;

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        try(DoctorVacationAccess doctorVacationAccess = new DoctorVacationAccess()) {

            validateSession(session, doctorVacationAccess, false);

            if (!hasPermission("io.hospital.vacation.read_all", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<DoctorVacation> doctorVacations = doctorVacationAccess.getAll();
            JSONResponse<List<DoctorVacation>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(doctorVacations);

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

        DoctorVacation doctorVacation = gson.fromJson(params,DoctorVacation.class);

        try(DoctorVacationAccess vacation_db = new DoctorVacationAccess()) {

            //validate session
            validateSession(session,vacation_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.vacation.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            vacation_db.upsert(doctorVacation);

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

        String id = params.get("doctorID").getAsString();

        try(DoctorVacationAccess access = new DoctorVacationAccess()){

            //validate session.
            validateSession(session,access,false);

            //validate permissions.
            if(!hasPermission("io.hospital.vacation.delete",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            access.delete(id);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }
}
