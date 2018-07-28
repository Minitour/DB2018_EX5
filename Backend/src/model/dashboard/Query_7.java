package model.dashboard;

import com.google.gson.annotations.Expose;
import database.DBObject;

public class Query_7 extends DBObject {

    @Expose
    private int hospitalID;

    @Expose
    private String name;

    @Expose
    private int departmentID;

    @Expose
    private String departmentName;

    @Expose
    private String ManagerName;

    @Expose
    private int numOfDoctors;

    public Query_7(int hospitalID, String name, int departmentID, String departmentName, String ManagerName, int numOfDoctors) {
        this.hospitalID = hospitalID;
        this.name = name;
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.ManagerName = ManagerName;
        this.numOfDoctors = numOfDoctors;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public String getName() {
        return name;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public int getNumOfDoctors() {
        return numOfDoctors;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public void setNumOfDoctors(int numOfDoctors) {
        this.numOfDoctors = numOfDoctors;
    }
}
