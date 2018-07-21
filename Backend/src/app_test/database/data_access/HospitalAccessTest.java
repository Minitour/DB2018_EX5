package database.data_access;

import model.Hospital;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HospitalAccessTest {

    static HospitalAccess access;

    @BeforeAll
    public static void first() {
        access = new HospitalAccess();
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

        String hospitalID = "2";

        List<Hospital> hospitals = access.getById(hospitalID);

        if (hospitals.size() == 0) {
            System.out.println("no hospitals");
        }
        else {
            hospitals.forEach(System.out::println);
        }

    }

    @Test
    void getAll() {

        List<Hospital> hospitals = access.getAll();

        if (hospitals.size() == 0) {
            System.out.println("no hospitals");
        }
        else {
            hospitals.forEach(System.out::println);
        }
    }

}