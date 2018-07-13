package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Department extends DBObject{

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private String departmentName;

    @Expose
    private int maxCapacity;

}
