package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

public class PatientsInMonth extends DBObject {

    @Expose
    private Integer month;

    @Expose
    private Integer patients;

    public PatientsInMonth(Integer month, Integer patients) {
        this.month = month;
        this.patients = patients;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getPatients() {
        return patients;
    }

    public void setPatients(Integer patients) {
        this.patients = patients;
    }
}
