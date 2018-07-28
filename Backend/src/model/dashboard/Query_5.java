package model.dashboard;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

public class Query_5 extends DBObject {

    @Expose
    private String ID;

    @Expose
    private String firstName;

    @Expose
    private String surName;

    @Expose
    private Date checkTime;

    @Expose
    private String bloodPressure;

    @Expose
    private float bodyTemp;

    public Query_5(String ID, String firstName, String surName, Date checkTime, String bloodPressure, float bodyTemp) {
        this.ID = ID;
        this.firstName = firstName;
        this.surName = surName;
        this.checkTime = checkTime;
        this.bloodPressure = bloodPressure;
        this.bodyTemp = bodyTemp;
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

    public Date getCheckTime() {
        return checkTime;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public float getBodyTemp() {
        return bodyTemp;
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

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setBodyTemp(float bodyTemp) {
        this.bodyTemp = bodyTemp;
    }
}
