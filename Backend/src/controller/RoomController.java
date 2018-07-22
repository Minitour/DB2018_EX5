package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.DepartmentAccess;
import database.data_access.RoomAccess;
import model.Department;
import model.Room;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

public class RoomController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        int hospitalID = params.get("hospitalID").getAsInt();
        int departmentID = params.get("departmentID").getAsInt();
        int roomNumber = params.get("roomNumber").getAsInt();

        try(RoomAccess roomAccess = new RoomAccess()) {

            validateSession(session, roomAccess, false);

            if (!hasPermission("io.hospital.room.read", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<Room> room = roomAccess.getById(hospitalID, departmentID, roomNumber);

            if (room.size() != 1)
                return JSONResponse.FAILURE().message("Room Not Found.");

            Room r = room.get(0);

            return JSONResponse.SUCCESS().data(r);

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
        int departmentID = params.get("departmentID").getAsInt();

        try(RoomAccess roomAccess = new RoomAccess()) {

            validateSession(session, roomAccess, false);

            if (!hasPermission("io.hospital.room.read_all", session)) {
                return JSONResponse.FAILURE().message("Access Denied.");
            }

            List<Room> rooms = roomAccess.getAll(hospitalID, departmentID);
            JSONResponse<List<Room>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(rooms);

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

        Room room = gson.fromJson(params.get("room"),Room.class);

        try(RoomAccess room_db = new RoomAccess()) {

            //validate session
            validateSession(session,room_db,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.room.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            room_db.upsert(room);

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
        int roomNumber = params.get("roomNumber").getAsInt();

        try(RoomAccess access = new RoomAccess()){

            //validate session.
            validateSession(session,access,false);

            //validate permissions.
            if(!hasPermission("io.hospital.room.delete",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            access.delete(hospitalID, departmentID, roomNumber);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }
}
