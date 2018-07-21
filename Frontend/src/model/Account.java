package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Account{

    @Expose
    private Integer ACCOUNT_ID;

    @Expose(serialize = false,deserialize = false)
    private String USER_PASSWORD;

    @Expose
    private String EMAIL;

    @Expose
    private Short ROLE_ID;



    // This class returns ACCOUNT_ID as InsertedId

}
