package model.join;

import database.Join;
import model.Doctor;

/**
 * Created By Tony on 24/07/2018
 */
public class HospitalJoinDepartmentJoinDoctor extends Join<HospitalJoinDepartment, Doctor> {

    public HospitalJoinDepartmentJoinDoctor() {
        super(HospitalJoinDepartment.class, Doctor.class);
    }
}
