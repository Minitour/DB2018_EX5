package controller;

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

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class LoginController implements RESTRoute {


    @Override
    public Object handle(Request request, Response response, JsonObject body, Session _ignored) throws Exception {

        long startTime = System.currentTimeMillis();

        String email = body.get("EMAIL").getAsString();
        String password = body.get("PASSWORD").getAsString();

        require(email,password);


        try(AccountAccess account_db = new AccountAccess();
            SessionAccess session_db = new SessionAccess(account_db)) {

            long now = System.currentTimeMillis();
            System.out.println(now - startTime);
            startTime = now;

            //get account with email
            List<Account> accounts = account_db.getById(email,null);

            now = System.currentTimeMillis();
            System.out.println(now - startTime);
            startTime = now;

            //
            if (accounts.size() != 1)
                throw new Exception("Account not found.");

            Account account = accounts.get(0);

            //get account id
            Integer accountId = account.getACCOUNT_ID();

            boolean doesMatch = BCrypt.checkpw(password,account.getUSER_PASSWORD());

            if(!doesMatch)
                throw new Exception("Password does not match!");

            //generate new session token
            String token = Utils.generateToken();

            Session session = new Session(accountId,token);
            session_db.upsert(session);

            now = System.currentTimeMillis();
            System.out.println(now - startTime);

            return JSONResponse.SUCCESS().data(session);
        }catch (Exception e){
            return JSONResponse.FAILURE().message(e.getMessage());
        }
    }
}
