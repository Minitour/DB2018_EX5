package network.api;

import model.Hospitalized;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class HospitalizedAPI extends GenericAPI<Hospitalized> {
    /**
     * @param url the base endpoint url.
     */
    public HospitalizedAPI() {
        super(Constants.Routes.hospitalized());
    }
}
