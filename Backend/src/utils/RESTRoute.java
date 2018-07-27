package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.Database;
import database.data_access.AccountAccess;
import database.data_access.SessionAccess;
import model.Session;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Antonio Zaitoun on 09/02/2018.
 */
@FunctionalInterface
public interface RESTRoute extends Route {

    @Override
    default Object handle(Request request, Response response) throws Exception{
        try {
            response.header("Content-Type", "application/json");
            JsonObject o = new Gson().fromJson(request.body(), JsonObject.class);

            return handle(request, response, o, extractFromBody(o));
        }catch (Exception e){
            return JSONResponse.FAILURE().message(e.getMessage());
        }
    }

    Object handle(Request request, Response response, JsonObject body, Session session) throws Exception;

    default Session extractFromBody(JsonObject body){
        try {
            int id = body.get("ACCOUNT_ID").getAsInt();
            String sessionToken = body.get("SESSION_TOKEN").getAsString();
            return new Session(id,sessionToken);
        }catch (NullPointerException e){
            return null;
        }
    }

    /**
     * validate session with new connection
     * @param session
     */
    default void validateSession(Session session){
        validateSession(session,null,true);
    }

    /**
     * Use this method to validate a session token.
     *
     * @param session a session that was extracted from the client.
     * @param existingConnection an existing database connection (if present). use null to create a new connection.
     */
    default void validateSession(Session session,Database existingConnection,boolean closeOnFinish) {
        //create session from existing connection if not null
        SessionAccess session_db =
                existingConnection == null ? new SessionAccess() : new SessionAccess(existingConnection);

        //init account db
        AccountAccess account_db = new AccountAccess(session_db);
        try {

            List<Session> sessionList = session_db.getById(session.ACCOUNT_ID,session.SESSION_TOKEN);

            if(sessionList.size() != 1)
                return;

            Session storedSession = sessionList.get(0);

            session.CREATION_DATE = storedSession.CREATION_DATE;

            //get account id
            int role = account_db.getById(session.ACCOUNT_ID,null,null).get(0).getROLE_ID();

            session.setRole(role);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(existingConnection == null)
                closeOnFinish = true;

            if(closeOnFinish)
                try {
                    account_db.close();
                } catch (Exception ignored) { }
        }
    }

    /**
     * Checks if the current session has permission for this certain action.
     *
     * @param permission
     * @param session
     * @return
     */
    default boolean hasPermission(String permission, Session session){
        return Permissions.hasPermissionFor(permission,session);
    }

    default void require(Object... values){
        for (Object value : values)
            if(value == null)
                throw new RuntimeException("Missing Parameter!");
    }

    default boolean requireNotZero(Number... numbers){
        for (Number number : numbers) {
            if(number.equals(0))
                return false;
        }
        return true;
    }
}
