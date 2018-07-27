package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Payment {

    @Expose
    private Integer serialNumber;

    @Expose
    private Integer minDay;

    @Expose
    private Integer maxDay;

    @Expose
    private Integer amount;

    public Payment(Integer serialNumber) { this.serialNumber = serialNumber; }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public Integer getMinDay() {
        return minDay;
    }

    public Integer getMaxDay() {
        return maxDay;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setMinDay(Integer minDay) {
        this.minDay = minDay;
    }

    public void setMaxDay(Integer maxDay) {
        this.maxDay = maxDay;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
