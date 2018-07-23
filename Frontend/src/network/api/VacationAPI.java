package network.api;

import model.DoctorVacation;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class VacationAPI extends GenericAPI<DoctorVacation> {
    /**
     * @param url the base endpoint url.
     */
    public VacationAPI() {
        super(Constants.Routes.vacation());
    }
}
