package model.join;

import com.google.gson.annotations.Expose;
import database.Join;
import model.Hospital;
import model.Hospitalized;

import java.sql.Date;

/**
 * Created By Tony on 24/07/2018
 */
public class HospitalJoinHospitalized extends Join<Hospital, Hospitalized> {

    @Expose
    private Integer month;

    @Expose
    private Integer totalPayment;

    public HospitalJoinHospitalized() {
        super(Hospital.class,Hospitalized.class);
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getTotalPayment() {
        return totalPayment;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setTotalPayment(Integer totalPayment) {
        this.totalPayment = totalPayment;
    }
}
