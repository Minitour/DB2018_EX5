package database.data_access;

import model.Department;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentAccessTest {


    static DepartmentAccess access;

    @BeforeAll
    public static void first() {
        access = new DepartmentAccess();
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
    void getById(int hospitalID, int departmentID) {

        int hospitalIDTemp = 1;
        int departmentIDTemp = 2;

        List<Department> departments = access.getById(hospitalIDTemp,departmentIDTemp);

        departments.forEach(System.out::println);

    }

    @Test
    void getAll() {

        List<Department> departments = access.getAll();

        departments.forEach(System.out::println);

    }


}