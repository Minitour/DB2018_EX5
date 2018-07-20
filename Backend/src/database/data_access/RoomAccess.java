package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.Room;

/**
 * Created By Tony on 19/07/2018
 */
public class RoomAccess extends GenericAccess<Room> {
    public RoomAccess() { super(Room.class,"Room"); }
    public RoomAccess(Database db) { super(Room.class,"Room",db); }
}
