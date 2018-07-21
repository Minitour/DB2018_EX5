package database.data_access;

import model.Shift;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShiftAccessTest {

    static ShiftAccess access;

    @BeforeAll
    public static void first() {
        access = new ShiftAccess();
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

        int shiftnumber = 1;

        List<Shift> shifts = access.getById(shiftnumber);

        if (shifts.size() == 0) {
            System.out.println("no shifts");
        }
        else {
            shifts.forEach(System.out::println);
        }
    }

    @Test
    void getAll() {

        List<Shift> shifts = access.getAll();

        shifts.forEach(System.out::println);

    }

}