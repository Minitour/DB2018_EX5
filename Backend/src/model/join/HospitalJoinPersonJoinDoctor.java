package model.join;

import database.Join;
import model.Doctor;

/**
 * Created By Tony on 24/07/2018
 */
public class HospitalJoinPersonJoinDoctor extends Join<HospitalJoinPerson, Doctor> {
    public HospitalJoinPersonJoinDoctor() {
        super(HospitalJoinPerson.class, Doctor.class);
    }
}
