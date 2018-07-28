package database.data_access;

import database.DBObject;
import database.Database;
import database.access.DataAccess;
import model.Person;
import model.join.*;

import java.util.List;

/**
 * Created By Tony on 24/07/2018
 */
public class QueriesAccess extends DataAccess<DBObject> {


    public QueriesAccess() { }

    public QueriesAccess(Database existingDatabase) {
        super(existingDatabase);
    }

    /**
     * TODO: Add query description
     * @return
     */
    public List<HospitalJoinPerson> query2(){
        return get(HospitalJoinPerson.class,"QUERY_2");
    }

    /**
     * TODO: Add query description
     * @return
     */
    public List<HospitalJoinDepartmentJoinDoctor> query3() {
        return get(HospitalJoinDepartmentJoinDoctor.class,"QUERY_3");
    }

    /**
     * TODO: Add query description
     * @return
     */
    public List<HospitalJoinPersonJoinDoctor> query4() {
        return get(HospitalJoinPersonJoinDoctor.class,"QUERY_4");
    }

    /**
     * TODO: Add query description
     * @param doctorID
     * @return
     */
    public List<PersonJoinCheckedBy> query5(String doctorID) {
        return get(PersonJoinCheckedBy.class,"QUERY_5", doctorID);
    }

    /**
     * TODO: Add query description
     * @return
     */
    public List<HospitalJoinHospitalized> query6(){
        return get(HospitalJoinHospitalized.class,"QUERY_6");
    }

    /**
     * TODO: Add query description
     * @return
     */
    public List<HospitalJoinDepartment> query7A(){
        return get(HospitalJoinDepartment.class,"QUERY_7A");
    }
    /**
     * TODO: Add query description
     * @return
     */
    public List<HospitalJoinDepartmentJoinDoctor> query7B(){
        return get(HospitalJoinDepartmentJoinDoctor.class,"QUERY_7B");
    }

    /**
     * TODO: Add query description
     * @return
     */
    public List<Person> query8(){
        return get(Person.class,"QUERY_8");
    }

    /**
     * TODO: Add query description
     * @return
     */
    public List<HospitalJoinDepartment> query9(){
        return get(HospitalJoinDepartment.class,"QUERY_9");
    }

    /**
     * TODO: Add query description
     * @param patientId
     * @return
     */
    public List<Person> query11(String patientId){
        return get(Person.class,"QUERY_11",patientId);
    }

}
