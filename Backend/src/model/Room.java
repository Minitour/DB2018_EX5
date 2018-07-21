package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Room extends DBObject {

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private int roomNumber;

    @Expose
    private int bedsAmount;

    public Room() {}

    public Room(int hospitalID, int departmentID, int roomNumber, int bedsAmount) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.roomNumber = roomNumber;
        this.bedsAmount = bedsAmount;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getBedsAmount() {
        return bedsAmount;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setBedsAmount(int bedsAmount) {
        this.bedsAmount = bedsAmount;
    }
}
