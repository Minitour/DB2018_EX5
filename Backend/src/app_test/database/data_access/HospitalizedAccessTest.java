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

        String patiendID = "";
        int eventCode = 0;
        int hospitalID = 0;
        int depID = 0;
        int roomNumber = 0;



        List<Hospitalized> hospitalizations = access.getById();

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