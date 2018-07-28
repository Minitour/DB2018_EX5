package model.dashboard;

import com.google.gson.annotations.Expose;
import database.DBObject;

public class Query_4 extends DBObject {

    @Expose
    private String name;

    @Expose
    private String doctorID;

    @Expose
    private String firstName;

    @Expose
    private String surName;

    public Query_4(String name, String doctorID, String firstName, String surName) {
        this.name = name;
        this.doctorID = doctorID;
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
