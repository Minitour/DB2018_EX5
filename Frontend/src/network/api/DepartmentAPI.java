package network.api;

import model.Department;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class DepartmentAPI extends GenericAPI<Department>{
    /**
     * @param url the base endpoint url.
     */
    public DepartmentAPI() {
        super(Constants.Routes.department());
    }
}
