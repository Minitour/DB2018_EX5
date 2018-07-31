package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Hospital implements Searchable {

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

}
