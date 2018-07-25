package network.api;

import model.CheckedBy;
import network.generic.GenericAPI;
import utils.Constants;

public class CheckedByAPI extends GenericAPI<CheckedBy> {
    /**
     * @param url the base endpoint url.
     */
    public CheckedByAPI() { super(Constants.Routes.checkedBy(),CheckedBy.class);}

}
