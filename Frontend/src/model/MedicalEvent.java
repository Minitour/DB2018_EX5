package model;

import com.google.gson.annotations.Expose;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class MedicalEvent  {
    @Expose
    private int eventCode;

    @Expose
    private String description;

    @Expose
    private int typeCode;

}
