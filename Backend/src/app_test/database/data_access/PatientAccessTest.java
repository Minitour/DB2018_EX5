package database.data_access;

import model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientAccessTest {

    static PatientAccess access;

    @BeforeAll
    public static void first() {
        access = new PatientAccess();
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

        String patientID = "343434343";

        List<Person> patients = access.getById(patientID);

        patients.forEach(System.out::println);
    }

    @Test
    void getAll() {

        List<Person> patients = access.getAll();

        patients.forEach(System.out::println);
    }

    @Test
    void delete() {
        
    }

}