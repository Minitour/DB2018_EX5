package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Hospital {

    @Expose
    private Integer hospitalID;

    @Expose
    private String name;

    @Expose
    private String city;

    @Expose
    private String street;

    @Expose
    private String phone;

    public Hospital(Integer hospitalID) { this.hospitalID = hospitalID; }


    public Integer getHospitalID() {
        return hospitalID;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPhone() {
        return phone;
    }
}
