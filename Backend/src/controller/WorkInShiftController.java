package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.WorkInShiftAccess;
import model.Session;
import model.Shift;
import model.WorkInShift;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class WorkInShiftController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        // getting the specified shift and doctor
        String doctorID = params.get("doctorID").getAsString();
        int shiftNumber = params.get("shiftNumber").getAsInt();

        try(WorkInShiftAccess workInShiftAccess = new WorkInShiftAccess()) {

            validateSession(session, workInShiftAccess, false);

            if (!hasPermission("io.hospital.work.read", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<WorkInShift> shifts = workInShiftAccess.getById(doctorID, shiftNumber);
            JSONResponse<List<WorkInShift>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(shifts);

            return jsonResponse;

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object readAll(JsonObject body, Session session) {

        try(WorkInShiftAccess workInShiftAccess = new WorkInShiftAccess()) {

            validateSession(session, workInShiftAccess, false);

            if (!hasPermission("io.hospital.work.read_all", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<WorkInShift> shifts = workInShiftAccess.getAll(session.ACCOUNT_ID);
            JSONResponse<List<WorkInShift>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(shifts);

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

        WorkInShift workInShift = gson.fromJson(params.get("department"),WorkInShift.class);

        try(WorkInShiftAccess workInShift_db = new WorkInShiftAccess()) {

            //validate session
            validateSession(session,workInShift_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.work.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            workInShift_db.upsert(workInShift);

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

        // getting the specified shift and doctor
        String doctorID = params.get("doctorID").getAsString();
        int shiftNumber = params.get("shiftNumber").getAsInt();

        try(WorkInShiftAccess access = new WorkInShiftAccess()){

            //validate session.
            validateSession(session,access,false);

            //validate permissions.
            if(!hasPermission("io.hospital.work.delete",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            access.delete(doctorID, shiftNumber);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }
}
