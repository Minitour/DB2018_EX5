package network.api;

import model.MedicalEventTypes;
import network.generic.GenericAPI;
import utils.Constants;

public class EventTypesAPI extends GenericAPI<MedicalEventTypes> {
    /**
     * @param url the base endpoint url.
     */
    public EventTypesAPI() { super(Constants.Routes.eventTypes()); }
}
