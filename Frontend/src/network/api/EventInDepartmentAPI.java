package network.api;

import model.MedicalEventTypeInDepartment;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class EventInDepartmentAPI extends GenericAPI<MedicalEventTypeInDepartment> {
    /**
     * @param url the base endpoint url.
     */
    public EventInDepartmentAPI() {
        super(Constants.Routes.eventInDep());
    }
}
