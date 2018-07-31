package model;

import com.google.gson.annotations.Expose;
import utils.Searchable;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEventTypeInDepartment implements Searchable {

    @Expose
    private Integer hospitalID;

    @Expose
    private Integer departmentID;

    @Expose
    private Integer typeCode;

    public MedicalEventTypeInDepartment(Integer hospitalID) {
        this.hospitalID = hospitalID;
    }

    public MedicalEventTypeInDepartment(Integer hospitalID, Integer departmentID, Integer typeCode) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.typeCode = typeCode;
    }

    public Integer getHospitalID() {
        return hospitalID;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public Integer getTypeCode() {
        return typeCode;
    }
}
