package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Shift {

    @Expose
    private Integer shiftNumber;

    @Expose
    private String dayInWeek;

    @Expose
    private String shiftType;

    public Shift(Integer shiftNumber) { this.shiftNumber = shiftNumber; }

    public Integer getShiftNumber() {
        return shiftNumber;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

}
