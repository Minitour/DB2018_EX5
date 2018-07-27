package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEventTypes {
    @Expose
    private Integer typeCode;

    @Expose
    private String typeName;

    public MedicalEventTypes(Integer typeCode) { this.typeCode = typeCode; }

    public Integer getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
