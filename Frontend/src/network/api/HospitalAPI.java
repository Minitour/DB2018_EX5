package network.api;

import model.Hospital;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class HospitalAPI extends GenericAPI<Hospital>{
    /**
     * @param url the base endpoint url.
     */
    public HospitalAPI() {
        super(Constants.Routes.hospital(),Hospital.class);
    }
}
