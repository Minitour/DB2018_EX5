package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Department {

    @Expose
    private Integer hospitalID;

    @Expose
    private Integer departmentID;

    @Expose
    private String departmentName;

    @Expose
    private Integer maxCapacity;

    public Department(int hospitalID, int departmentID) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
    }
}
