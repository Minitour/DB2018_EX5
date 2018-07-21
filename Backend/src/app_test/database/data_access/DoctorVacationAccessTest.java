package database.data_access;

import model.DoctorVacation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoctorVacationAccessTest {
    static DoctorVacationAccess access;

    @BeforeAll
    public static void first() {
        access = new DoctorVacationAccess();
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

        String doctorID = "323232323";

        List<DoctorVacation> vacations = access.getById(doctorID);

        if (vacations.size() == 0) {
            System.out.println("no vacations");
        }
        else {
            vacations.forEach(System.out::println);
        }

    }

    @Test
    void getAll() {

        List<DoctorVacation> vacations = access.getAll();

        if (vacations.size() == 0) {
            System.out.println("no vacations");
        }
        else {
            vacations.forEach(System.out::println);
        }
    }

}