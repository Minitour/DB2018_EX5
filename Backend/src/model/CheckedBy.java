package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class CheckedBy extends DBObject {

    @Expose
    private String patientID;

    @Expose
    private int eventCode;

    @Expose
    private String doctorID;

    @Expose
    private int shiftNumber;

    @Expose
    private Date checkTime;

    @Expose
    private Double bodyTemp;

    @Expose
    private String bloodPressure;

    public CheckedBy() { }

    public CheckedBy(String patientID, int eventCode, String doctorID, int shiftNumber, Date checkTime, Double bodyTemp, String bloodPressure) {
        this.patientID = patientID;
        this.eventCode = eventCode;
        this.doctorID = doctorID;
        this.shiftNumber = shiftNumber;
        this.checkTime = checkTime;
        this.bodyTemp = bodyTemp;
        this.bloodPressure = bloodPressure;
    }

    public String getPatientID() {
        return patientID;
    }

    public int getEventCode() {
        return eventCode;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public Double getBodyTemp() {
        return bodyTemp;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public void setBodyTemp(Double bodyTemp) {
        this.bodyTemp = bodyTemp;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }


}
