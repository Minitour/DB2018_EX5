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
    private int eventCode;

    @Expose
    private String doctorID;

    @Expose
    private int shiftNumber;

    @Expose
    private Date checkTime;

    @Expose
    private float bodyTemp;

    @Expose
    private String bloodPressure;
}
