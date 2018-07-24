package model.join;

import database.Join;
import model.Department;
import model.Hospital;

/**
 * Created By Tony on 24/07/2018
 */
public class HospitalJoinDepartment extends Join<Hospital, Department> {

    public HospitalJoinDepartment() {
        super(Hospital.class, Department.class);
    }
}
