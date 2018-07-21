package model;

import com.google.gson.annotations.Expose;

import java.sql.Date;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Session {

    @Expose
    public Integer ACCOUNT_ID;

    @Expose
    public String SESSION_TOKEN;

}
