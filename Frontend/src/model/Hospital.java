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

    public Hospital(int hospitalID) { this.hospitalID = hospitalID; }

}
