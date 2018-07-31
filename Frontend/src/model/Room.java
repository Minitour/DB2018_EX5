package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Room implements Searchable {

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

    public Room(Integer hospitalID, Integer departmentID) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
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

    public void setHospitalID(Integer hospitalID) {
        this.hospitalID = hospitalID;
    }

}
