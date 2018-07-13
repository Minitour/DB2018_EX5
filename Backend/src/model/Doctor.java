package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Doctor extends DBObject {

    @Expose
    private String doctorId;

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private Date dateOfGraduation;

    @Expose
    private int manager;

}
