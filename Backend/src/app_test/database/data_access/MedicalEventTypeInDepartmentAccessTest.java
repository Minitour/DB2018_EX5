package database.data_access;

import model.MedicalEventTypeInDepartment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalEventTypeInDepartmentAccessTest {

    static MedicalEventTypeInDepartmentAccess access;

    @BeforeAll
    public static void first() {
        access = new MedicalEventTypeInDepartmentAccess();
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

        int hospitalID = 2;
        int departmentID = 1;

        List<MedicalEventTypeInDepartment> medicals = access.getById(hospitalID, departmentID);

        if (medicals.size() == 0) {
            System.out.println("no medicals");
        }
        else {
            medicals.forEach(System.out::println);
        }

    }

    @Test
    void getAll() {

        int hospitalID = 2;

        List<MedicalEventTypeInDepartment> medicals = access.getAll(hospitalID);

        if (medicals.size() == 0) {
            System.out.println("no medicals");
        }
        else {
            medicals.forEach(System.out::println);
        }
    }

}