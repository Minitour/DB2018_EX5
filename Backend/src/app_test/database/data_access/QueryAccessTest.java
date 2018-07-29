package database.data_access;

import model.join.DashboardHospitalJoinHospitalized;
import model.join.HospitalJoinDepartment;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QueryAccessTest {

    @Test
    void test() {

        try(QueriesAccess queriesAccess = new QueriesAccess()) {


            System.out.println("For Query 13");
            List<DashboardHospitalJoinHospitalized> list = queriesAccess.query13(1002);
            list.forEach(System.out::println);
            System.out.println("\n");


            System.out.println("For Query 13A");
            List<DashboardHospitalJoinHospitalized> list2 = queriesAccess.query13A();
            list2.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
