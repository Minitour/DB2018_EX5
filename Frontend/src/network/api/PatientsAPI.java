package network.api;

import model.Person;
import network.Constants;
import network.generic.GenericAPI;

/**
 * Created By Tony on 23/07/2018
 */
public class PatientsAPI extends GenericAPI<Person> {

    public PatientsAPI() { super(Constants.Routes.patients()); }

}
