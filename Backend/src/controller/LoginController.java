package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import database.data_access.AccountAccess;
import database.data_access.SessionAccess;
import model.Account;
import model.Session;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;
import utils.JSONResponse;
import utils.RESTRoute;
import utils.Utils;

import java.util.List;
import java.util.Optional;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class LoginController implements RESTRoute {


    /**
     *
     * @param request The request object.
     * @param response The response object.
     * @param body the request body.
     * @param _ignored no session needed here.
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response, JsonObject body, Session _ignored) throws Exception {

        JsonElement emailJson = body.get("EMAIL");
        JsonElement passwordJson = body.get("PASSWORD");

        //require not null objects
        require(emailJson,passwordJson);

        String email = emailJson.getAsString();
        String password = passwordJson.getAsString();


        //Always use try-with resources to close the connections automatically at the end of the execution.

        try(    //create account access with new connection
                AccountAccess account_db = new AccountAccess();

                //create session access with existing connection
                SessionAccess session_db = new SessionAccess(account_db)
        ) {

            //get account with email
            List<Account> accounts = account_db.getById(email,null);

            //make sure there is only one account that matches.
            if (accounts.size() != 1)
                throw new Exception("Account not found.");

            //get instance
            Account account = accounts.get(0);

            //get account id
            Integer accountId = account.getACCOUNT_ID();

            //check password
            boolean doesMatch = BCrypt.checkpw(password,account.getUSER_PASSWORD());

            //password does not match
            if(!doesMatch)
                throw new Exception("Password does not match!");

            //generate new session token
            String token = Utils.generateToken();

            Session session = new Session(accountId,token);
            session_db.upsert(session);
            return JSONResponse.SUCCESS().data(session);
        }catch (Exception e){
            return JSONResponse.FAILURE().message(e.getMessage());
        }
    }
}
