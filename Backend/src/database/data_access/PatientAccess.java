package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.Person;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class PatientAccess extends GenericAccess<Person> {
    public PatientAccess(Database db) {
        super(Person.class, "Patient", db);
    }

    public PatientAccess() {
        super(Person.class, "Patient");
    }
}
