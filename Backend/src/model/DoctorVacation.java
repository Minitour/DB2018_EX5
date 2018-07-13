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

}
