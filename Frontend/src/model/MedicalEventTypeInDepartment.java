package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEventTypeInDepartment {

    @Expose
    private Integer hospitalID;

    @Expose
    private Integer departmentID;

    @Expose
    private Integer typeCode;

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
