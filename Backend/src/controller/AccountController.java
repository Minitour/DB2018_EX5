package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import database.data_access.AccountAccess;
import database.data_access.SessionAccess;
import model.Account;
import model.Session;
import utils.GenericController;
import org.mindrot.jbcrypt.BCrypt;
import utils.JSONResponse;
import utils.Utils;

import java.util.List;

public class AccountController extends GenericController {


    @Override
    public Object upsert(JsonObject body, Session session) {

        JsonObject params = parameters(body);

        JsonElement account_id = params.get("ACCOUNT_ID");
        JsonElement session_token = params.get("SESSION_TOKEN");
        JsonElement cPassword = params.get("currentPassword");
        JsonElement nPassword = params.get("newPassword");

        //require not null objects
        require(account_id,session_token, cPassword, nPassword);

        String ACCOUNT_ID = account_id.getAsString();
        String SESSION_TOKEN = session_token.getAsString();
        String currentPassword = cPassword.getAsString();
        String newPassword = nPassword.getAsString();

        try(    //create account access with new connection
                AccountAccess account_db = new AccountAccess();

                SessionAccess session_db = new SessionAccess();

        ) {

            // get account with ACCOUNT_ID
            List<Account> accounts = account_db.getById(ACCOUNT_ID, null);

            // get the session for this user
            List<Session> sessions = session_db.getById(ACCOUNT_ID, SESSION_TOKEN);

            // make sure there is only one account that matches.
            if (accounts.size() != 1)
                return JSONResponse.FAILURE().message("Account not found.");

            // making sure there is a session in the db
            if (sessions.size() < 1)
                return JSONResponse.FAILURE().message("Session does not exists for this user.");

            // get the account
            Account account = accounts.get(0);

            // get the current stored password
            String storedPassword = account.getUSER_PASSWORD();

            // check password with the entered one
            boolean doesMatch = BCrypt.checkpw(currentPassword,storedPassword);

            // passwords do not match
            if(!doesMatch)
                return JSONResponse.FAILURE().message("Password does not match!");

            // assing new updated account
            Account updatedAccount = account;

            // encrypt the password
            String encrypedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            // set the new password in the desired account
            account.setUSER_PASSWORD(encrypedPassword);

            // update the account with the new password
            account_db.upsert(updatedAccount);

            return JSONResponse.SUCCESS();
        }catch (Exception e){
            return JSONResponse.FAILURE().message(e.getMessage());
        }

    }
}
