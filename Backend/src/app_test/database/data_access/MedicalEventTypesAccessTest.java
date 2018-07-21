package database.data_access;

import model.MedicalEventTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalEventTypesAccessTest {

    static MedicalEventTypesAccess access;

    @BeforeAll
    public static void first() {
        access = new MedicalEventTypesAccess();
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

        int typeCode = 1;

        List<MedicalEventTypes> medicals = access.getById(typeCode);

        if (medicals.size() == 0) {
            System.out.println("no medicals");
        }
        else {
            medicals.forEach(System.out::println);
        }
    }

    @Test
    void getAll() {

        List<MedicalEventTypes> medicals = access.getAll();

        if (medicals.size() == 0) {
            System.out.println("no medicals");
        }
        else {
            medicals.forEach(System.out::println);
        }
    }

}