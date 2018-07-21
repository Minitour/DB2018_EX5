package database.data_access;

import model.Room;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomAccessTest {

    static RoomAccess access;

    @BeforeAll
    public static void first() {
        access = new RoomAccess();
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

        int hospitalID = 1002;
        int departmentID = 2;
        int roomNumber = 1;

        List<Room> rooms = access.getById(hospitalID, departmentID, roomNumber);

        rooms.forEach(System.out::println);

    }

    @Test
    void getAll() {

        int hospitalID = 1002;
        int departmentID = 2;

        List<Room> rooms = access.getAll(hospitalID, departmentID);

        rooms.forEach(System.out::println);
    }

}