package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class WorkInShift extends DBObject {

    @Expose
    private String doctorID;

    @Expose
    private int shiftNumber;

    public WorkInShift() {}

    public WorkInShift(String doctorID, int shiftNumber) {
        this.doctorID = doctorID;
        this.shiftNumber = shiftNumber;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }
}
