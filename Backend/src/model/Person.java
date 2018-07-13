package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Person extends DBObject{
    @Expose
    private String ID;

    @Expose
    private String firstName;

    @Expose
    private String surName;

    @Expose
    private Date birthOfBirth;

    @Expose
    private String city;

    @Expose
    private String street;

    @Expose
    private Character gender;

    @Expose
    private String phone;

    @Expose
    private String bloodType;

    @Expose
    private String careFacility;

    @Expose
    private String contactID;

    @Expose
    private Account ACCOUNT_ID;
}
