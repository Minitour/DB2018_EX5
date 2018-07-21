package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Department {

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private String departmentName;

    @Expose
    private int maxCapacity;
}
