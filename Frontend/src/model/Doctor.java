package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Doctor implements Searchable {

    @Expose
    private String doctorID;

    @Expose
    private Integer hospitalID;

    @Expose
    private Integer departmentID;

    @Expose
    private Date dateOfGraduation;

    @Expose
    private Integer manager;

    public Doctor(String doctorID) { this.doctorID = doctorID; }

    public String getDoctorID() {
        return doctorID;
    }

    public Integer getHospitalID() {
        return hospitalID;
    }

}
