package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Payment extends DBObject {

    @Expose
    private int serialNumber;

    @Expose
    private int minDay;

    @Expose
    private int maxDay;

    @Expose
    private int amount;
}
