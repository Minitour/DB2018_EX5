package model;

import com.google.gson.annotations.Expose;
import database.DBObject;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Account extends DBObject{

    @Expose
    private Integer ACCOUNT_ID;

    @Expose(serialize = false,deserialize = false)
    private String USER_PASSWORD;

    @Expose
    private String EMAIL;

    @Expose
    private Short ROLE_ID;


    public Account(Integer ACCOUNT_ID, String EMAIL, Short ROLE_ID, String USER_PASSWORD) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.EMAIL = EMAIL;
        this.ROLE_ID = ROLE_ID;
        this.USER_PASSWORD = BCrypt.hashpw(USER_PASSWORD,BCrypt.gensalt());
    }

    public Account(Integer ACCOUNT_ID, String EMAIL, Short ROLE_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.EMAIL = EMAIL;
        this.ROLE_ID = ROLE_ID;
    }

    public Account(String EMAIL, Short ROLE_ID, String USER_PASSWORD) {
        this.EMAIL = EMAIL;
        this.ROLE_ID = ROLE_ID;
        this.USER_PASSWORD = BCrypt.hashpw(USER_PASSWORD,BCrypt.gensalt());
    }

    public Integer getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(Integer ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Short getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(Short ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }
}
