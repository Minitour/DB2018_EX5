package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class WorkInShift implements Searchable {

    @Expose
    private String doctorID;

    @Expose
    private Integer shiftNumber;

    public WorkInShift() {}

    public WorkInShift(String doctorID, Integer shiftNumber) {
        this.doctorID = doctorID;
        this.shiftNumber = shiftNumber;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }



}
