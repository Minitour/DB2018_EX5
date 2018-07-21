package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Shift extends DBObject {

    @Expose
    private int shiftNumber;

    @Expose
    private String dayInWeek;

    @Expose
    private String shiftType;

    public Shift() {}

    public Shift(int shiftNumber, String dayInWeek, String shiftType) {
        this.shiftNumber = shiftNumber;
        this.dayInWeek = dayInWeek;
        this.shiftType = shiftType;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public void setDayInWeek(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }
}
