package database.data_access;

import model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonAccessTest {

    static PersonAccess access;

    @BeforeAll
    public static void first() {
        access = new PersonAccess();
    }


    @AfterAll
    public static void finish() {
        try {
            access.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getById() {

        String ID = "323232323";

        List<Person> persons = access.getById(ID);

        if (persons.size() == 0) {
            System.out.println("no person");
        }
        else {
            persons.forEach(System.out::println);
        }
    }

    @Test
    void getAll() {

        List<Person> persons = access.getAll();

        if (persons.size() == 0) {
            System.out.println("no persons");
        }
        else {
            persons.forEach(System.out::println);
        }
    }

}