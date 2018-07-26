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

    public Room(int hospitalID, int departmentID, int roomNumber) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.roomNumber = roomNumber;
    }
}
