package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Account{

    @Expose
    private Integer ACCOUNT_ID;

    @Expose
    private String USER_PASSWORD;

    @Expose
    private String EMAIL;

    @Expose
    private Short ROLE_ID;

    @Expose
    private Integer hospitalID;


    public Account(Integer ACCOUNT_ID) { this.ACCOUNT_ID = ACCOUNT_ID; }
    // This class returns ACCOUNT_ID as InsertedId


    public Integer getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public Short getROLE_ID() {
        return ROLE_ID;
    }

    public Integer getHospitalID() {
        return hospitalID;
    }
}
