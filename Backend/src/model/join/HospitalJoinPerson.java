package model.join;

import database.Join;
import model.Hospital;
import model.Person;

/**
 * Created By Tony on 24/07/2018
 */
public class HospitalJoinPerson extends Join<Hospital, Person> {
    public HospitalJoinPerson() {
        super(Hospital.class, Person.class);
    }
}
