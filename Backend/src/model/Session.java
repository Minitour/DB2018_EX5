package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Session extends DBObject {

    @Expose
    public final Integer ACCOUNT_ID;

    @Expose
    public final String SESSION_TOKEN;

    @Expose
    public transient Date CREATION_DATE = new Date(System.currentTimeMillis());

    private int role = -1;

    public Session(Integer ACCOUNT_ID, String SESSION_TOKEN) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.SESSION_TOKEN = SESSION_TOKEN;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isValid() {
        return role != -1;
    }

    public int getRole() {
        return role;
    }
}
