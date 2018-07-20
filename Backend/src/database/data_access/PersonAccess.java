package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.Person;

/**
 * Created By Tony on 19/07/2018
 */
public class PersonAccess extends GenericAccess<Person> {
    public PersonAccess() { super(Person.class,"Person"); }
    public PersonAccess(Database db) { super(Person.class,"Person",db); }
}
