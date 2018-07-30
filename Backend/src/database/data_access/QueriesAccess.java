package database.data_access;

import database.DBObject;
import database.Database;
import database.access.DataAccess;
import model.Hospitalized;
import model.Person;
import model.join.*;

import java.util.ArrayList;
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
     * this query returns all the top 10 senior doctors in hospital
     * @return
     */
    public List<HospitalJoinPerson> query2(){
        return get(HospitalJoinPerson.class,"QUERY_2");
    }

    /**
     * this query returns all the infected hospitals
     * @return
     */
    public List<HospitalJoinDepartmentJoinDoctor> query3() {
        return get(HospitalJoinDepartmentJoinDoctor.class,"QUERY_3");
    }

    /**
     * this query returns the status of the hospital (busy, not busy, regular)
     * @return
     */
    public List<HospitalJoinPersonJoinDoctor> query4() {
        return get(HospitalJoinPersonJoinDoctor.class,"QUERY_4");
    }

    /**
     * GIVEN doctorID this query return the patients of this doctor and the latest check
     * @param doctorID
     * @return
     */
    public List<PersonJoinCheckedBy> query5(String doctorID) {
        return get(PersonJoinCheckedBy.class,"QUERY_5", doctorID);
    }

    /**
     * this query returns the income from patients by HOSPITAL
     * @return
     */
    public List<HospitalJoinDepartment> query6(int hospitalID){
        return get(HospitalJoinDepartment.class,"QUERY_6", hospitalID);
    }

    /**
     * this query returns the number of doctors in a department
     * @return
     */
    public List<HospitalJoinDepartment> query7A(){
        return get(HospitalJoinDepartment.class,"QUERY_7A");
    }
    /**
     * this query returns for every type of department the lowest ratio of doctors in all hospitals
     * @return
     */
    public List<HospitalJoinDepartmentJoinDoctor> query7B(){
        return get(HospitalJoinDepartmentJoinDoctor.class,"QUERY_7B");
    }

    /**
     * this query returns all potential donors for bloodtypes
     * @return
     */
    public List<Person> query8(){
        return get(Person.class,"QUERY_8");
    }

    /**
     * thie query returns the status of the department: high, medium, low (busy status)..
     * @return
     */
    public List<HospitalJoinDepartment> query9(Integer hospitalId){
        return get(HospitalJoinDepartment.class,"QUERY_9",hospitalId);
    }

    /**
     * GIVEN ID of a patient this query will return if he is hipochonder
     * @param patientId
     * @return
     */
    public List<Person> query11(String patientId){
        return get(Person.class,"QUERY_11",patientId);
    }

    /**
     * this query returns the number of patients in a hospital
     * @param
     * @return
     */
    public List<DashboardHospitalJoinHospitalized> query13(int hospitalID){
        return get(DashboardHospitalJoinHospitalized.class,"QUERY_13", hospitalID);
    }

    /**
     * this query returns the number of patients per hospital
     * @param
     * @return
     */
    public List<DashboardHospitalJoinHospitalized> query13A(){
        return get(DashboardHospitalJoinHospitalized.class,"QUERY_13A");
    }

    /**
     * this query returns the number of patients per city
     * @param
     * @return
     */
    public List<DashboardHospitalJoinHospitalized> query14(int hospitalID){
        return get(DashboardHospitalJoinHospitalized.class,"QUERY_14", hospitalID);
    }



}
