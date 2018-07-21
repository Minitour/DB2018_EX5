package database.data_access;

import model.MedicalEvent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalEventAccessTest {

    static MedicalEventAccess access;

    @BeforeAll
    public static void first() {
        access = new MedicalEventAccess();
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

        int eventCode = 1;

        List<MedicalEvent> medicals = access.getById(eventCode);

        if (medicals.size() == 0) {
            System.out.println("no medicals");
        }
        else {
            medicals.forEach(System.out::println);
        }

    }

    @Test
    void getAll() {

        List<MedicalEvent> medicals = access.getAll();

        if (medicals.size() == 0) {
            System.out.println("no medicals");
        }
        else {
            medicals.forEach(System.out::println);
        }
    }

}