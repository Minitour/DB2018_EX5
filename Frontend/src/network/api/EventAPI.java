package network.api;

import model.MedicalEvent;
import network.generic.GenericAPI;
import utils.Constants;

import java.awt.*;

/**
 * Created By Tony on 23/07/2018
 */
public class EventAPI extends GenericAPI<MedicalEvent> {
    /**
     * @param url the base endpoint url.
     */
    public EventAPI() {
        super(Constants.Routes.event());
    }
}
