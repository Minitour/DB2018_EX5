package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Doctor extends DBObject {

    @Expose
    private String doctorID;

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private Date dateOfGraduation;

    @Expose
    private boolean manager;

    public Doctor() {}

    public Doctor(String doctorId, int hospitalID, int departmentID, Date dateOfGraduation, boolean manager) {
        this.doctorID = doctorId;
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.dateOfGraduation = dateOfGraduation;
        this.manager = manager;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public Date getDateOfGraduation() {
        return dateOfGraduation;
    }

    public boolean getManager() {
        return manager;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setDateOfGraduation(Date dateOfGraduation) {
        this.dateOfGraduation = dateOfGraduation;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
