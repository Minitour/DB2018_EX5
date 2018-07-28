package model.dashboard;

import com.google.gson.annotations.Expose;
import database.DBObject;

public class Query_9 extends DBObject {

    @Expose
    private String name;

    @Expose
    private String departmentName;

    @Expose
    private double freeBedsInDepartment;

    @Expose
    private String status;

    public Query_9(String name, String departmentName, double freeBedsInDepartment, String status) {
        this.name = name;
        this.departmentName = departmentName;
        this.freeBedsInDepartment = freeBedsInDepartment;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public double getFreeBedsInDepartment() {
        return freeBedsInDepartment;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setFreeBedsInDepartment(double freeBedsInDepartment) {
        this.freeBedsInDepartment = freeBedsInDepartment;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
