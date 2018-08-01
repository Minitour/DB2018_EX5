package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Account implements Searchable {

    @Expose
    private Integer ACCOUNT_ID;

    @Expose
    private String EMAIL;

    @Expose
    private String USER_PASSWORD;

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

    public String roleLiteral(){
        switch (getROLE_ID()){
            case 1:
                return "Patient";
            case 2:
                return "Secretary";
            case 3:
                return "Doctor";
            case 4:
                return "Doctor Manager";
            case 5:
                return "Admin";
            case 6:
                return "Super User";
            default:
                return "Unknown";
        }
    }
}
