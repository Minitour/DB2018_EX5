package model;

import com.google.gson.annotations.Expose;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class DoctorVacation {

    @Expose
    private String doctorID;

    @Expose
    private Date vacationDate;

    public DoctorVacation(String doctorID) { this.doctorID = doctorID; }

    public String getDoctorID() {
        return doctorID;
    }

    public Date getVacationDate() {
        return vacationDate;
    }
}
