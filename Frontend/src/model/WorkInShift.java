package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class WorkInShift  {

    @Expose
    private String doctorID;

    @Expose
    private Integer shiftNumber;

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
