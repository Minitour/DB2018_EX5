package database.data_access;

import database.GenericAccess;
import model.Person;

/**
 * Created By Tony on 19/07/2018
 */
public class PersonAccess extends GenericAccess<Person> {
    public PersonAccess() { super(Person.class,"Person"); }
}
