package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class DoctorVacation extends DBObject {

    @Expose
    private String doctorID;

    @Expose
    private Date vacationDate;

    public DoctorVacation() {}

    public DoctorVacation(String doctorID, Date vacationDate) {
        this.doctorID = doctorID;
        this.vacationDate = vacationDate;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public Date getVacationDate() {
        return vacationDate;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setVacationDate(Date vacationDate) {
        this.vacationDate = vacationDate;
    }
}
