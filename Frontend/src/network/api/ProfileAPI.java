package network.api;

import model.Person;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class ProfileAPI extends GenericAPI<Person> {
    /**
     * @param url the base endpoint url.
     */
    public ProfileAPI() {
        super(Constants.Routes.profile(),Person.class);
    }
}
