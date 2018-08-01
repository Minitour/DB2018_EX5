package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Shift implements Searchable {

    final static String [] daysOfTheweek = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

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

    public String getShiftType() {
        return shiftType;
    }


    public String dayLiteralValue(){
        return daysOfTheweek[Integer.parseInt(dayInWeek)-1];
    }

    public String typeLiteralValue(){
        return shiftType.equals("M") ? "Morning" : "Evening";
    }
}
