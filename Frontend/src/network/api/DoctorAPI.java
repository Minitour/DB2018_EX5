package network.api;

import model.Doctor;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class DoctorAPI extends GenericAPI<Doctor> {
    /**
     * @param url the base endpoint url.
     */
    public DoctorAPI() {
        super(Constants.Routes.doctor(),Doctor.class);
    }
}
