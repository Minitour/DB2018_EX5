package database.data_access;

import model.WorkInShift;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkInShiftAccessTest {

    static WorkInShiftAccess access;

    @BeforeAll
    public static void first() {
        access = new WorkInShiftAccess();
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

        String doctorID = "343434343";
        int shiftNumber = 1;

        List<WorkInShift> wis = access.getById(doctorID, shiftNumber);

        wis.forEach(System.out::println);

    }

    @Test
    void getAll() {

        List<WorkInShift> wis = access.getAll();

        wis.forEach(System.out::println);
    }

}