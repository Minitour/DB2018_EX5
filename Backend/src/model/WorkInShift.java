package model;

import com.google.gson.annotations.Expose;
import database.DBObject;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class WorkInShift extends DBObject {

    @Expose
    private String doctorID;

    @Expose
    private int shiftNumber;

}
