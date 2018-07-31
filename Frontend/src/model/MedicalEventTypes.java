package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEventTypes implements Searchable {
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

}
