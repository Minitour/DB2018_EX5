package model.join;

import com.google.gson.annotations.Expose;
import database.Join;
import model.Department;
import model.Hospital;

/**
 * Created By Tony on 24/07/2018
 */
public class HospitalJoinDepartment extends Join<Hospital, Department> {

    @Expose
    private Integer numOfDoctors;

    @Expose
    private Integer freeBedsInDepartment;

    @Expose
    private String status;

    public HospitalJoinDepartment() {
        super(Hospital.class, Department.class);
    }

    public Integer getNumOfDoctors() {
        return numOfDoctors;
    }

    public void setNumOfDoctors(Integer numOfDoctors) {
        this.numOfDoctors = numOfDoctors;
    }

    public Integer getFreeBedsInDepartment() {
        return freeBedsInDepartment;
    }

    public String getStatus() {
        return status;
    }

    public void setFreeBedsInDepartment(Integer freeBedsInDepartment) {
        this.freeBedsInDepartment = freeBedsInDepartment;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
