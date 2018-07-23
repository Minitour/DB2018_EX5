package model;

import com.google.gson.annotations.Expose;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Doctor {

    @Expose
    private String doctorID;

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private Date dateOfGraduation;

    @Expose
    private int manager;

    public Doctor(String doctorID) { this.doctorID = doctorID; }
}
