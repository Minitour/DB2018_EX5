package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Room {

    @Expose
    private Integer hospitalID;

    @Expose
    private Integer departmentID;

    @Expose
    private Integer roomNumber;

    @Expose
    private Integer bedsAmount;

    public Room(Integer hospitalID, Integer departmentID, Integer roomNumber) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.roomNumber = roomNumber;
    }

    public Integer getHospitalID() {
        return hospitalID;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Integer getBedsAmount() {
        return bedsAmount;
    }

    public void setHospitalID(Integer hospitalID) {
        this.hospitalID = hospitalID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setBedsAmount(Integer bedsAmount) {
        this.bedsAmount = bedsAmount;
    }
}
