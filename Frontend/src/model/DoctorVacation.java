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

}
