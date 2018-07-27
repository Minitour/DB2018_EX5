package network.api;

import model.Shift;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class ShiftAPI extends GenericAPI<Shift> {
    /**
     * @param url the base endpoint url.
     */
    public ShiftAPI() {
        super(Constants.Routes.shift(),Shift.class);
    }
}
