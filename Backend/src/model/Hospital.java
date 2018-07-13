package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Hospital extends DBObject {

    @Expose
    private int hospitalID;

    @Expose
    private String name;

    @Expose
    private String city;

    @Expose
    private String street;

    @Expose
    private String phone;

}
