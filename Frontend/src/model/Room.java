package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Room {

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private int roomNumber;

    @Expose
    private int bedsAmount;
}
