package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Shift {

    @Expose
    private int shiftNumber;

    @Expose
    private String dayInWeek;

    @Expose
    private String shiftType;
}