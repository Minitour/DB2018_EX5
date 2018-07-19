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
}
