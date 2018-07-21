package database.data_access;

import model.CheckedBy;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckedByAccessTest {

    static CheckedByAccess access;

    @BeforeAll
    public static void first() {
        access = new CheckedByAccess();
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
    void upsert() {

        String patientID = "343434343";
        int eventCode = 1;
        String doctorID = "343434343".replace("4", "2");
        int shiftNumber = 2;
        Date checkTime = new Date(System.currentTimeMillis());
        Double bodyTemp = 37.0;
        String bloodPressure = "90/90";

        int temp = access.upsert(patientID, eventCode, doctorID, shiftNumber, checkTime, bodyTemp, bloodPressure);

        System.out.println(temp);

        /*
    [patientID],
	[eventCode],
	[doctorID],
	[shiftNumber],
	[checkTime],
	[bodyTemp],
	[bloodPressure]
         */




    }

    @Test
    void getById() {

        String patientID = "343434343";
        int eventCode = 1;
        String doctorID = "343434343".replace("4", "2");
        int shiftNumber = 2;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String tempdate = "21-07-2018";

        LocalDate date = LocalDate.parse(tempdate, formatter);

        List<CheckedBy> checks = access.getById(patientID, eventCode, doctorID, shiftNumber, date);

        checks.forEach(System.out::println);

    }

    @Test
    void getAll() {

        List<CheckedBy> checks = access.getAll();

        checks.forEach(System.out::println);
    }

    @Test
    void delete() {

        String patientID = "343434343";
        int eventCode = 1;
        String doctorID = "343434343".replace("4", "2");
        int shiftNumber = 2;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String tempdate = "21-07-2018";

        LocalDate date = LocalDate.parse(tempdate, formatter);

        access.delete(patientID, eventCode, doctorID, shiftNumber, date);
    }

}