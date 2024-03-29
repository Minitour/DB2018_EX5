package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Department implements Searchable {

    @Expose
    private Integer hospitalID;

    @Expose
    private Integer departmentID;

    @Expose
    private String departmentName;

    @Expose
    private Integer maxCapacity;

    public Department(Integer hospitalID) {
        this.hospitalID = hospitalID;
    }

    public Department(int hospitalID, int departmentID) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
    }

    public Integer getHospitalID() {
        return hospitalID;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
