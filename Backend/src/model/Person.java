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
    private Date dateOfBirth;

    @Expose
    private String city;

    @Expose
    private String street;

    @Expose
    private String gender;

    @Expose
    private String phone;

    @Expose
    private String bloodType;

    @Expose
    private String careFacility;

    @Expose
    private String contactID;

    @Expose
    private Integer ACCOUNT_ID;

    public Person() {}

    public Person(String ID, String firstName, String surName, Date dateOfBirth, String city, String street, String gender, String phone, String bloodType, String careFacility, String contactID, Integer ACCOUNT_ID) {
        this.ID = ID;
        this.firstName = firstName;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.street = street;
        this.gender = gender;
        this.phone = phone;
        this.bloodType = bloodType;
        this.careFacility = careFacility;
        this.contactID = contactID;
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getCareFacility() {
        return careFacility;
    }

    public String getContactID() {
        return contactID;
    }

    public Integer getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setCareFacility(String careFacility) {
        this.careFacility = careFacility;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public void setACCOUNT_ID(Integer ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }
}
