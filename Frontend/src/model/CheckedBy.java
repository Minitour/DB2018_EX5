package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class CheckedBy implements Searchable {

    @Expose
    private String patientID;

    @Expose
    private Integer eventCode;

    @Expose
    private String doctorID;

    @Expose
    private Integer shiftNumber;

    @Expose
    private Date checkTime;

    @Expose
    private Float bodyTemp;

    @Expose
    private String bloodPressure;

    public CheckedBy(String patientID, Integer eventCode, String doctorID, Integer shiftNumber, Date checkTime) {
        this.patientID = patientID;
        this.eventCode = eventCode;
        this.doctorID = doctorID;
        this.shiftNumber = shiftNumber;
        this.checkTime = checkTime;
    }

    public String getPatientID() {
        return patientID;
    }

    public Integer getEventCode() {
        return eventCode;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public Integer getShiftNumber() {
        return shiftNumber;
    }
}
