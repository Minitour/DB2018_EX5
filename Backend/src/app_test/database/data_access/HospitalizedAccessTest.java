package database.data_access;

import model.Hospitalized;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HospitalizedAccessTest {

    static HospitalizedAccess access;

    @BeforeAll
    public static void first() {
        access = new HospitalizedAccess();
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

        String patiendID = "343434343";
        int eventCode = 1;
        int hospitalID = 1002;
        int depID = 2;
        short roomNumber = 1;

        List<Hospitalized> hospitalizations = access.getById(patiendID, eventCode, hospitalID, depID, roomNumber);

        if (hospitalizations.size() == 0) {
            System.out.println("no hospitals");
        }
        else {
            hospitalizations.forEach(System.out::println);
        }
    }

    @Test
    void getAll() {

        List<Hospitalized> hospitalizations = access.getAll();

        if (hospitalizations.size() == 0) {
            System.out.println("no hospitals");
        }
        else {
            hospitalizations.forEach(System.out::println);
        }
    }

}