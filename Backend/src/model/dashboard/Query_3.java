package model.dashboard;

import com.google.gson.annotations.Expose;
import database.DBObject;

public class Query_3 extends DBObject {

    @Expose
    private String name;

    @Expose
    private String hospitalStatus;

    public Query_3(String name, String hospitalStatus) {
        this.name = name;
        this.hospitalStatus = hospitalStatus;
    }

    public String getName() {
        return name;
    }

    public String getHospitalStatus() {
        return hospitalStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHospitalStatus(String hospitalStatus) {
        this.hospitalStatus = hospitalStatus;
    }
}
