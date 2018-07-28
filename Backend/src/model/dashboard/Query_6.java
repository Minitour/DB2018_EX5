package model.dashboard;

import com.google.gson.annotations.Expose;
import database.DBObject;

public class Query_6 extends DBObject {

    @Expose
    private String name;

    @Expose
    private Integer month;

    @Expose
    private Integer totalPayment;

    public Query_6(String name, Integer month, Integer totalPayment) {
        this.name = name;
        this.month = month;
        this.totalPayment = totalPayment;
    }

    public String getName() {
        return name;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getTotalPayment() {
        return totalPayment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setTotalPayment(Integer totalPayment) {
        this.totalPayment = totalPayment;
    }
}
