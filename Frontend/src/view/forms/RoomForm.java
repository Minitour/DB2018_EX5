package view.forms;

import model.Room;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class RoomForm extends UIFormView<Room> {

    public RoomForm(Room existingValue, OnFinish<Room> callback) {
        super(Room.class, existingValue, callback);
    }
}
