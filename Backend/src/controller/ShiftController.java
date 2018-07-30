package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.ShiftAccess;
import model.Session;
import model.Shift;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class ShiftController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        int shiftNumber = params.get("shiftNumber").getAsInt();

        try(ShiftAccess shiftAccess = new ShiftAccess()) {

            validateSession(session, shiftAccess, false);

            if (!hasPermission("io.hospital.shift.read", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<Shift> shifts = shiftAccess.getById(shiftNumber);
            JSONResponse<List<Shift>> jsonResponse = JSONResponse.SUCCESS();
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

        try(ShiftAccess shiftAccess = new ShiftAccess()) {

            validateSession(session, shiftAccess, false);

            if (!hasPermission("io.hospital.shift.read_all", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<Shift> shifts = shiftAccess.getAll();
            JSONResponse<List<Shift>> jsonResponse = JSONResponse.SUCCESS();
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

        Shift shift = gson.fromJson(body.get("parameters"),Shift.class);

        try(ShiftAccess shift_db = new ShiftAccess()) {

            //validate session
            validateSession(session,shift_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.shift.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            shift_db.upsert(shift);

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

        int shiftNumber = params.get("shiftNumber").getAsInt();

        try(ShiftAccess shiftAccess = new ShiftAccess()) {

            validateSession(session, shiftAccess, false);

            if (!hasPermission("io.hospital.shift.delete", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            shiftAccess.delete(shiftNumber);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }
}
