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

    public MedicalEventTypeInDepartment(int hospitalID, int departmentID, int typeCode) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.typeCode = typeCode;
    }

}
