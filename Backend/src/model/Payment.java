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

    public Payment() {}

    public Payment(int serialNumber, int minDay, int maxDay, int amount) {
        this.serialNumber = serialNumber;
        this.minDay = minDay;
        this.maxDay = maxDay;
        this.amount = amount;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getMinDay() {
        return minDay;
    }

    public int getMaxDay() {
        return maxDay;
    }

    public int getAmount() {
        return amount;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setMinDay(int minDay) {
        this.minDay = minDay;
    }

    public void setMaxDay(int maxDay) {
        this.maxDay = maxDay;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
