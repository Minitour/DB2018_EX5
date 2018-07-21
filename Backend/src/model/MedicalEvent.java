package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEvent extends DBObject {
    @Expose
    private int eventCode;

    @Expose
    private String description;

    @Expose
    private int typeCode;

    public MedicalEvent() {}

    public MedicalEvent(int eventCode, String description, int typeCode) {
        this.eventCode = eventCode;
        this.description = description;
        this.typeCode = typeCode;
    }

    public int getEventCode() {
        return eventCode;
    }

    public String getDescription() {
        return description;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
