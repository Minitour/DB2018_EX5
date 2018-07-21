package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Doctor extends DBObject {

    @Expose
    private String doctorId;

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private Date dateOfGraduation;

    @Expose
    private int manager;

    public Doctor() {}

    public Doctor(String doctorId, int hospitalID, int departmentID, Date dateOfGraduation, int manager) {
        this.doctorId = doctorId;
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.dateOfGraduation = dateOfGraduation;
        this.manager = manager;
    }

    public String getDoctorId() {
        return doctorId;
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

    public int getManager() {
        return manager;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public void setManager(int manager) {
        this.manager = manager;
    }
}
