package model.join;

import com.google.gson.annotations.Expose;
import database.Join;
import model.Doctor;

/**
 * Created By Tony on 24/07/2018
 */
public class HospitalJoinDepartmentJoinDoctor extends Join<HospitalJoinDepartment, Doctor> {

    @Expose
    private String hospitalStatus;

    @Expose
    private Integer numOfDoctors;

    public HospitalJoinDepartmentJoinDoctor() {
        super(HospitalJoinDepartment.class, Doctor.class);
    }

    public String getHospitalStatus() {
        return hospitalStatus;
    }

    public void setHospitalStatus(String hospitalStatus) {
        this.hospitalStatus = hospitalStatus;
    }

    public void setNumOfDoctors(Integer numOfDoctors) {
        this.numOfDoctors = numOfDoctors;
    }

    public Integer getNumOfDoctors() {
        return numOfDoctors;
    }
}
