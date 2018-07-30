package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import database.data_access.AccountAccess;
import database.data_access.PersonAccess;
import database.data_access.SessionAccess;
import model.Account;
import model.Person;
import model.Session;
import org.mindrot.jbcrypt.BCrypt;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Antonio Zaitoun on 27/07/2018.
 */
public class AccountController extends GenericController {

    /**
     * get account by id
     *
     * @param body
     * @param session
     * @return
     */
    @Override
    public Object read(JsonObject body, Session session) {
        //method not needed
        JsonObject params = parameters(body);
        String email = params.get("EMAIL").getAsString();

        try(AccountAccess account_db = new AccountAccess()){

            validateSession(session,account_db,false);

            if(!hasPermission("io.hospital.account.read",session))
                return JSONResponse.FAILURE().message("Access Denied");

            List<Account> accounts = account_db.getById(null,email,0);
            if(accounts.size() != 1)
                return JSONResponse.FAILURE().message("Account not found.");

            Account account = accounts.get(0);
            account.setUSER_PASSWORD(null);

            return JSONResponse
                    .SUCCESS()
                    .data(account);

        }catch (Exception e){
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    /**
     * get all accounts
     *
     * @param body
     * @param session
     * @return
     */
    @Override
    public Object readAll(JsonObject body, Session session) {
        try(AccountAccess account_db = new AccountAccess()){

            validateSession(session,account_db,false);

            if(!hasPermission("io.hospital.account.read_all",session))
                return JSONResponse.FAILURE().message("Access Denied");

            Account currentAccount = account_db.getById(session.ACCOUNT_ID,null,0).get(0);
            int currentHospital = currentAccount.getHospitalID();

            //TODO: get only for current hospital
            List<Account> accounts = account_db.getAll(session.getRole() == 6 ? 0 : currentHospital);

            return JSONResponse
                    .SUCCESS()
                    .data(accounts);

        }catch (Exception e){
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    /**
     * delete certain account
     *
     * @param body
     * @param session
     * @return
     */
    @Override
    public Object delete(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        int id = params.get("ACCOUNT_ID").getAsInt();

        if(id == session.ACCOUNT_ID)
            return JSONResponse
                    .FAILURE()
                    .message("Deleting your own account is not a valid action.");


        try(
                AccountAccess account_db = new AccountAccess();
                SessionAccess session_db = new SessionAccess(account_db);
                PersonAccess person_db = new PersonAccess(account_db)
        ){

            //check session
            validateSession(session,account_db,false);

            //check if user has permission to delete accounts
            if(!hasPermission("io.hospital.account.delete",session))
                return JSONResponse.FAILURE().message("Access Denied");

            //get existing sessions
            List<Session> sessions = session_db
                    .getAll()
                    .stream()
                    .filter(session1 -> session1.ACCOUNT_ID == id)
                    .collect(Collectors.toList());

            //delete sessions from db
            for (Session userSession : sessions)
                session_db.delete(id,userSession.SESSION_TOKEN);

            //get account owners
            List<Person> accountOwner = person_db.getById(id,null);

            //remove all owners from the db
            //TODO: consider removing account id only.
            if(accountOwner.size() > 0)
                for(Person p : accountOwner)
                    person_db.delete(p.getID());


            //delete account
            account_db.delete(null,id);

            return JSONResponse.SUCCESS();
        }catch (Exception e){
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    /**
     *
     * create or update an account
     *
     * @param body
     * @param session
     * @return
     */
    @Override
    public Object upsert(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        String password = params.get("USER_PASSWORD").getAsString();
        Gson gson = new Gson();
        Account account = gson.fromJson(params,Account.class);

        if(Objects.equals(account.getACCOUNT_ID(), session.ACCOUNT_ID))
            return JSONResponse
                    .FAILURE()
                    .message("You cannot modify your own account.");

        try(AccountAccess account_db = new AccountAccess()){

            validateSession(session,account_db,false);

            if(!hasPermission("io.hospital.account.upsert",session))
                return JSONResponse
                        .FAILURE()
                        .message("Access Denied");


            //check that account doesn't exist already
            List<Account> existingAccounts =
                    account_db.getById(null,account.getEMAIL(),0);

            if(existingAccounts.size() > 0){
                if(session.getRole() != 6)
                    return JSONResponse
                            .FAILURE()
                            .message("You do not have a permission to update an account.");


                Account existing = existingAccounts.get(0);

                account.setACCOUNT_ID(existing.getACCOUNT_ID());
                account.setUSER_PASSWORD(existing.getUSER_PASSWORD());

            }else {
                String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
                account.setUSER_PASSWORD(hashedPassword);
                account.setACCOUNT_ID(0);
            }

            short roleId = account.getROLE_ID();

            switch (session.getRole()){
                case 2:
                case 3:
                case 4:
                    roleId = 1;
                    break;
                case 5:
                    roleId = roleId > 5 || roleId < 1 ? 5 : roleId;
                    break;
            }

            account.setROLE_ID(roleId);

            Account currentAccount = account_db.getById(session.ACCOUNT_ID,null,0).get(0);
            int currentHospital = currentAccount.getHospitalID();

            if(session.getRole() != 6 && account.getHospitalID() != currentHospital)
                return JSONResponse
                        .FAILURE()
                        .message("Attempting to add an account to a hospital of which you are not a part of.");



            //insert account
            account_db.upsert(account);

            return JSONResponse.SUCCESS();
        }catch (Exception e){
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }

    }
}
