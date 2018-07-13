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
}
