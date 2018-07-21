package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEventTypeInDepartment extends DBObject {

    @Expose
    private int hospitalID;

    @Expose
    private int departmentID;

    @Expose
    private int typeCode;

    public MedicalEventTypeInDepartment() {}

    public MedicalEventTypeInDepartment(int hospitalID, int departmentID, int typeCode) {
        this.hospitalID = hospitalID;
        this.departmentID = departmentID;
        this.typeCode = typeCode;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
