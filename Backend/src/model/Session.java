package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

import java.sql.Timestamp;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Session extends DBObject {

    @Expose
    public final String ACCOUNT_ID;

    @Expose
    public final String SESSION_TOKEN;

    @Expose
    public Timestamp CREATION_DATE;

    private int role = -1;

    public Session(String ACCOUNT_ID, String SESSION_TOKEN, Timestamp CREATION_DATE) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.SESSION_TOKEN = SESSION_TOKEN;
        this.CREATION_DATE = CREATION_DATE;
    }

    public Session(String ACCOUNT_ID, String SESSION_TOKEN) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.SESSION_TOKEN = SESSION_TOKEN;
    }

    void setRole(int role) {
        this.role = role;
    }

    public boolean isValid() {
        return role != -1;
    }

    public int getRole() {
        return role;
    }
}
