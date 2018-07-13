package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Account extends DBObject{

    @Expose
    private String ACCOUNT_ID;

    @Expose
    private String EMAIL;

    @Expose
    private Integer ROLE_ID;

    @Expose(serialize = false,deserialize = false)
    private String USER_PASSWORD;

    public Account(String ACCOUNT_ID, String EMAIL, Integer ROLE_ID, String USER_PASSWORD) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.EMAIL = EMAIL;
        this.ROLE_ID = ROLE_ID;
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public Account(String ACCOUNT_ID, String EMAIL, Integer ROLE_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.EMAIL = EMAIL;
        this.ROLE_ID = ROLE_ID;
    }

    public Account(String EMAIL, Integer ROLE_ID, String USER_PASSWORD) {
        this.EMAIL = EMAIL;
        this.ROLE_ID = ROLE_ID;
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Integer getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(Integer ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }
}
