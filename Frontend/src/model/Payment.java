package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Payment implements Searchable {

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



}
