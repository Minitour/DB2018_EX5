package model.join_operations;

import database.data_access.QueriesAccess;
import model.Person;
import model.join.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HospitalJDepartmentJDoctorTest {


    @Test
    void test() throws Exception {

        QueriesAccess queriesAccess = new QueriesAccess();

        List<PersonJoinCheckedBy> personList = queriesAccess.query5("323232323");

        if (personList.size() > 0) {

            for (int i = 0; i < 2; i++) {
                System.out.println(personList.get(i).left().getID());
                System.out.println(personList.get(i).left().getFirstName());
                System.out.println(personList.get(i).left().getSurName());
                System.out.println(personList.get(i).right().getCheckTime());
                System.out.println(personList.get(i).right().getBloodPressure());
                System.out.println(personList.get(i).right().getBodyTemp());


                //System.out.println(hospitalJoinPeople.get(i).getStatus());
            }



        }
        //h.hospitalID, h.name, d.departmentID, d.departmentName,
        //m.ManagerName, count(doc.doctorID)as'numOfDoctors'
        //System.out.println(hospitalJoinPeople.get(0).getTotalPayment());

        //hospitalJoinPeople.forEach(System.out::println);

        queriesAccess.close();

    }

}
