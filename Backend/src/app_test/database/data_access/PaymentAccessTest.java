package database.data_access;

import model.Payment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentAccessTest {

    static PaymentAccess access;

    @BeforeAll
    public static void first() {
        access = new PaymentAccess();
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

        int serialNumber = 1;

        List<Payment> medicals = access.getById(serialNumber);

        if (medicals.size() == 0) {
            System.out.println("no medicals");
        }
        else {
            medicals.forEach(System.out::println);
        }
    }

    @Test
    void getAll() {

        List<Payment> medicals = access.getAll();

        if (medicals.size() == 0) {
            System.out.println("no medicals");
        }
        else {
            medicals.forEach(System.out::println);
        }

    }

}