package model.join_operations;

import database.data_access.QueriesAccess;
import model.join.HospitalJoinDepartment;
import model.join.HospitalJoinDepartmentJoinDoctor;
import model.join.HospitalJoinPerson;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HospitalJDepartmentJDoctorTest {


    @Test
    void test() throws Exception {

        QueriesAccess queriesAccess = new QueriesAccess();

        List<HospitalJoinPerson> hospitalJoinPeople = queriesAccess.query2();

        hospitalJoinPeople.forEach(System.out::println);

        queriesAccess.close();

    }

}
