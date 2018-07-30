package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.DepartmentAccess;
import database.data_access.HospitalAccess;
import model.Department;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class DepartmentController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        int hospitalID = params.get("hospitalID").getAsInt();
        int departmentID = params.get("departmentID").getAsInt();

        try(DepartmentAccess departmentAccess = new DepartmentAccess()) {

            validateSession(session, departmentAccess, false);

            if (!hasPermission("io.hospital.department.read", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<Department> department = departmentAccess.getById(hospitalID, departmentID);

            if (department.size() != 1)
                return JSONResponse.FAILURE().message("Department Not Found.");

            Department d = department.get(0);

            return JSONResponse.SUCCESS().data(d);

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

        try(DepartmentAccess departmentAccess = new DepartmentAccess()) {

            validateSession(session, departmentAccess, false);

            if (!hasPermission("io.hospital.department.read_all", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<Department> departments = departmentAccess.getAll(hospitalID);
            JSONResponse<List<Department>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(departments);

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

        Department department = gson.fromJson(body.get("parameters"),Department.class);

        try(DepartmentAccess department_db = new DepartmentAccess()) {

            //validate session
            validateSession(session,department_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.department.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            department_db.upsert(department);

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

        try(DepartmentAccess access = new DepartmentAccess()){

            //validate session.
            validateSession(session,access,false);

            //validate permissions.
            if(!hasPermission("io.hospital.department.delete",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            access.delete(hospitalID, departmentID);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }
}
