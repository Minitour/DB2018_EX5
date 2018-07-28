package model.dashboard;

import com.google.gson.annotations.Expose;
import database.DBObject;

public class Query_2 extends DBObject {

    @Expose
    private String ID;

    @Expose
    private String firstName;

    @Expose
    private String surName;

    @Expose
    private String name;

    public Query_2(String ID, String firstName, String surName, String name) {
        this.ID = ID;
        this.firstName = firstName;
        this.surName = surName;
        this.name = name;
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

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }
}
