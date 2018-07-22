package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.DepartmentAccess;
import database.data_access.HospitalAccess;
import model.Department;
import model.Hospital;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class HospitalController extends GenericController {


    @Override
    public Object delete(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        int hospitalID = params.get("hospitalID").getAsInt();

        try(HospitalAccess hospitalAccess = new HospitalAccess();
            DepartmentAccess departmentAccess = new DepartmentAccess(hospitalAccess)) {

            validateSession(session,hospitalAccess, false);

            validateSession(session, departmentAccess, false);

            if (!hasPermission("io.hospital.hospital.delete", session) ||
                    !hasPermission("io.hospital.department.delete", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            // getting the departments to delete under the hospital
            List<Department> departments = departmentAccess.getAll(hospitalID);

            // deleting the departments
            departments.forEach(department -> {
                int temp = department.getDepartmentID();
                departmentAccess.delete(hospitalID, temp);
            });

            hospitalAccess.delete(hospitalID);

            return JSONResponse.SUCCESS();


        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        int hospitalID = params.get("hospitalID").getAsInt();

        try(HospitalAccess hospitalAccess = new HospitalAccess()) {

            validateSession(session, hospitalAccess, false);

            if (!hasPermission("io.hospital.hospital.read", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<Hospital> hospital = hospitalAccess.getById(hospitalID);

            if (hospital.size() != 1)
                return JSONResponse.FAILURE().message("Hospital Not Found.");

            Hospital h = hospital.get(0);

            return JSONResponse.SUCCESS().data(h);

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        try(HospitalAccess hospitalAccess = new HospitalAccess()) {

            validateSession(session, hospitalAccess, false);

            if (!hasPermission("io.hospital.hospital.read_all", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<Hospital> hospitals = hospitalAccess.getAll();
            JSONResponse<List<Hospital>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(hospitals);

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

        Hospital hospital = gson.fromJson(params.get("hospital"),Hospital.class);

        try(HospitalAccess hospital_db = new HospitalAccess()) {

            //validate session
            validateSession(session,hospital_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.hospital.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            hospital_db.upsert(hospital);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }
}
