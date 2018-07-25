package model.join;

import database.Join;
import model.CheckedBy;
import model.Person;

public class PersonJoinCheckedBy extends Join<Person, CheckedBy> {

    public PersonJoinCheckedBy() {
        super(Person.class, CheckedBy.class);
    }

}
