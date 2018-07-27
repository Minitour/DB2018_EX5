package network.api;

import model.WorkInShift;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class WorkInShiftAPI extends GenericAPI<WorkInShift> {
    /**
     * @param url the base endpoint url.
     */
    public WorkInShiftAPI() {
        super(Constants.Routes.workInShift(),WorkInShift.class);
    }
}
