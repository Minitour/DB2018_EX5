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

    public Department(){}

    public Department(int hospitalID, int departmentID, String departmentName, int maxCapacity) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.maxCapacity = maxCapacity;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

}
