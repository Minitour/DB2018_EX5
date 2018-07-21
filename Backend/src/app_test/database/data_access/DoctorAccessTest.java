package database.data_access;

import model.Doctor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoctorAccessTest {

    static DoctorAccess access;

    @BeforeAll
    static void first() {
        access = new DoctorAccess();
    }

    @AfterAll
    static void finish() throws Exception {
        access.close();
    }


    @Test
    void getById() {

        String doctorID = "323232323";

        List<Doctor> doctors = access.getById(doctorID);

        doctors.forEach(System.out::println);


    }

    @Test
    void getAll() {

        List<Doctor> doctors = access.getAll();

        doctors.forEach(System.out::println);


    }

}