package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEvent implements Searchable {
    @Expose
    private Integer eventCode;

    @Expose
    private String discription;

    @Expose
    private Integer typeCode;

    public MedicalEvent(Integer eventCode) { this.eventCode = eventCode; }

    public Integer getEventCode() {
        return eventCode;
    }

    public String getDescription() {
        return discription;
    }

    public Integer getTypeCode() {
        return typeCode;
    }
}
