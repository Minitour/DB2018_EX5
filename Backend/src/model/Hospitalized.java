package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Hospitalized extends DBObject {

    @Expose
    private String patientID;

    @Expose
    private int eventCode;

    @Expose
    private int numberOfDays;

    @Expose
    private Date dateOfArrival;

    @Expose
    private int severityLevel;

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private int roomNumber;

    public Hospitalized() {}

    public Hospitalized(String patientID, int eventCode, int numberOfDays, Date dateOfArrival, int severityLevel, int hospitalID, int departmentID, int roomNumber) {
        this.patientID = patientID;
        this.eventCode = eventCode;
        this.numberOfDays = numberOfDays;
        this.dateOfArrival = dateOfArrival;
        this.severityLevel = severityLevel;
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.roomNumber = roomNumber;
    }

    public String getPatientID() {
        return patientID;
    }

    public int getEventCode() {
        return eventCode;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public void setSeverityLevel(int severityLevel) {
        this.severityLevel = severityLevel;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
