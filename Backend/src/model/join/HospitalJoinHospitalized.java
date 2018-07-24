package model.join;

import database.Join;
import model.Hospital;
import model.Hospitalized;

/**
 * Created By Tony on 24/07/2018
 */
public class HospitalJoinHospitalized extends Join<Hospital, Hospitalized> {
    public HospitalJoinHospitalized() {
        super(Hospital.class,Hospitalized.class);
    }
}
