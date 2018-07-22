package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import database.access.GenericAccess;
import database.data_access.DoctorAccess;
import database.data_access.PersonAccess;
import model.Doctor;
import model.Person;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import javax.print.Doc;
import java.util.List;

public class DoctorsController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session)       {

        JsonObject params = parameters(body);
        require(params);

        String doctorID = params.get("doctorID").getAsString();

        try (DoctorAccess doctorAccess = new DoctorAccess()) {

            validateSession(session,doctorAccess,false);

            if(!hasPermission("io.hospital.doctor.read", session))
                return JSONResponse.FAILURE().message("Access Denied.");


            List<Doctor> doctors = doctorAccess.getById(doctorID);

            if (doctors.size() != -1) {
                return JSONResponse.FAILURE().message("Doctor Not found.");
            }

            Doctor doctor = doctors.get(0);

            return JSONResponse.SUCCESS().data(doctor);


        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        try(DoctorAccess doctorAccess = new DoctorAccess()) {

            validateSession(session,doctorAccess,false);

            if(!hasPermission("io.hospital.doctor.read_all", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<Doctor> doctors = doctorAccess.getAll();
            JSONResponse<List<Doctor>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(doctors);

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

        Doctor d = gson.fromJson(params.get("doctor"),Doctor.class);

        try(DoctorAccess doctor_db = new DoctorAccess()) {

            //validate session
            validateSession(session,doctor_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.doctor.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            doctor_db.upsert(d);

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

        String doctorID = params.get("doctorID").getAsString();

        try(DoctorAccess doctorAccess = new DoctorAccess()){

            //validate session.
            validateSession(session,doctorAccess,false);

            //validate permissions.
            if(!hasPermission("io.hospital.doctor.delete",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            doctorAccess.delete(doctorID);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }

}
