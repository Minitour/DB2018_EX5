package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEvent extends DBObject {
    @Expose
    private int eventCode;

    @Expose
    private String discription;

    @Expose
    private int typeCode;



}
