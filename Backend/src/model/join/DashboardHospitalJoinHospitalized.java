package model.join;

import com.google.gson.annotations.Expose;
import database.Join;
import model.Hospital;
import model.Hospitalized;

public class DashboardHospitalJoinHospitalized extends Join<Hospital, Hospitalized> {


    @Expose
    private int number_of_patients;

    @Expose
    private int hospitalID;

    @Expose
    private String name;


    public DashboardHospitalJoinHospitalized() {
        super(Hospital.class, Hospitalized.class);
    }

    public int getNumber_of_patients() {
        return number_of_patients;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public String getName() {
        return name;
    }

    public void setNumber_of_patients(int number_of_patients) {
        this.number_of_patients = number_of_patients;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public void setName(String name) {
        this.name = name;
    }
}

