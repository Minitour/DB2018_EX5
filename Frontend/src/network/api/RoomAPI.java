package network.api;

import model.Room;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created By Tony on 23/07/2018
 */
public class RoomAPI extends GenericAPI<Room> {
    /**
     * @param url the base endpoint url.
     */
    public RoomAPI() {
        super(Constants.Routes.room(),Room.class);
    }
}
