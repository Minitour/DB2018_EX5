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
    private String discription;

    @Expose
    private int typeCode;

    public MedicalEvent() {}

    public MedicalEvent(int eventCode, String discription, int typeCode) {
        this.eventCode = eventCode;
        this.discription = discription;
        this.typeCode = typeCode;
    }

    public int getEventCode() {
        return eventCode;
    }

    public String getDiscription() {
        return discription;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
