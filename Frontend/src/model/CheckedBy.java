package model;

import com.google.gson.annotations.Expose;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class CheckedBy {

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

    public CheckedBy(String patientID, int eventCode, String doctorID, int shiftNumber, Date checkTime) {
        this.patientID = patientID;
        this.eventCode = eventCode;
        this.doctorID = doctorID;
        this.shiftNumber = shiftNumber;
        this.checkTime = checkTime;
    }
}
