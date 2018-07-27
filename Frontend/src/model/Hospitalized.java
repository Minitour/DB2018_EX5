package model;

import com.google.gson.annotations.Expose;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Hospitalized {

    @Expose
    private String patientID;

    @Expose
    private Integer eventCode;

    @Expose
    private Integer numberOfDays;

    @Expose
    private Date dateOfArrival;

    @Expose
    private Integer severityLevel;

    @Expose
    private Integer hospitalID;

    @Expose
    private Integer departmentID;

    @Expose
    private Integer roomNumber;

    public Hospitalized(String patientID, Integer eventCode) {
        this.patientID = patientID;
        this.eventCode = eventCode;
    }

    public Hospitalized(Integer hospitalID, Integer departmentID) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public Integer getEventCode() {
        return eventCode;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public Integer getSeverityLevel() {
        return severityLevel;
    }

    public Integer getHospitalID() {
        return hospitalID;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }
}
