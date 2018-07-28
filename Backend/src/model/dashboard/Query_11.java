package model.dashboard;

import com.google.gson.annotations.Expose;
import database.DBObject;

public class Query_11 extends DBObject {

    @Expose
    private String ID;

    @Expose
    private String firstName;

    @Expose
    private String surName;

    @Expose
    private String phone;

    public Query_11(String ID, String firstName, String surName, String phone) {
        this.ID = ID;
        this.firstName = firstName;
        this.surName = surName;
        this.phone = phone;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getPhone() {
        return phone;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
