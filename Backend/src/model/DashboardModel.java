package model;

import com.google.gson.annotations.Expose;
import model.join.*;

import java.util.List;

/**
 * Created By Tony on 28/07/2018
 */
public class DashboardModel {

    @Expose
    private List<HospitalJoinPerson> query2_result;

    @Expose
    private List<HospitalJoinDepartmentJoinDoctor> query3_result;

    @Expose
    private List<HospitalJoinPersonJoinDoctor> query4_result;

    @Expose
    private List<PersonJoinCheckedBy> query5_result;

    @Expose
    private List<HospitalJoinDepartment> query6_result;

    @Expose
    private List<HospitalJoinDepartment> query7A_result;

    @Expose
    private List<HospitalJoinDepartmentJoinDoctor> query7B_result;

    @Expose
    private List<Person> query8_result;

    @Expose
    private List<HospitalJoinDepartment> query9_result;

    @Expose
    private List<Person> query11_result;

    @Expose
    private List<DashboardHospitalJoinHospitalized> query13_result;

    @Expose
    private List<DashboardHospitalJoinHospitalized> query13A_result;


    public DashboardModel() {
    }

    public void setQuery2_result(List<HospitalJoinPerson> query2_result) {
        this.query2_result = query2_result;
    }

    public void setQuery3_result(List<HospitalJoinDepartmentJoinDoctor> query3_result) {
        this.query3_result = query3_result;
    }

    public void setQuery4_result(List<HospitalJoinPersonJoinDoctor> query4_result) {
        this.query4_result = query4_result;
    }

    public void setQuery5_result(List<PersonJoinCheckedBy> query5_result) {
        this.query5_result = query5_result;
    }

    public void setQuery6_result(List<HospitalJoinDepartment> query6_result) {
        this.query6_result = query6_result;
    }

    public void setQuery7A_result(List<HospitalJoinDepartment> query7A_result) {
        this.query7A_result = query7A_result;
    }

    public void setQuery7B_result(List<HospitalJoinDepartmentJoinDoctor> query7B_result) {
        this.query7B_result = query7B_result;
    }

    public void setQuery8_result(List<Person> query8_result) {
        this.query8_result = query8_result;
    }

    public void setQuery9_result(List<HospitalJoinDepartment> query9_result) {
        this.query9_result = query9_result;
    }

    public void setQuery11_result(List<Person> query11_result) {
        this.query11_result = query11_result;
    }

    public void setQuery13_result(List<DashboardHospitalJoinHospitalized> query13_result) {
        this.query13_result = query13_result;
    }

    public void setQuery13A_result(List<DashboardHospitalJoinHospitalized> query13A_result) {
        this.query13A_result = query13A_result;
    }
}
