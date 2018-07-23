package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Payment {

    @Expose
    private int serialNumber;

    @Expose
    private int minDay;

    @Expose
    private int maxDay;

    @Expose
    private int amount;

    public Payment(int serialNumber) { this.serialNumber = serialNumber; }
}
