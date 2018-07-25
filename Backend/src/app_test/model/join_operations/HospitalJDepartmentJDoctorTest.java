package model.join_operations;

import database.data_access.QueriesAccess;
import model.join.HospitalJoinDepartment;
import model.join.HospitalJoinDepartmentJoinDoctor;
import model.join.HospitalJoinHospitalized;
import model.join.HospitalJoinPerson;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HospitalJDepartmentJDoctorTest {


    @Test
    void test() throws Exception {

        QueriesAccess queriesAccess = new QueriesAccess();

        List<HospitalJoinHospitalized> hospitalJoinPeople = queriesAccess.query6();

        System.out.println(hospitalJoinPeople.get(0).getMonth());
        System.out.println(hospitalJoinPeople.get(0).getTotalPayment());

        //hospitalJoinPeople.forEach(System.out::println);

        queriesAccess.close();

    }

}
