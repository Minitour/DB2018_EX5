package view.tables;

import model.Room;
import network.api.PatientsAPI;
import network.api.RoomAPI;
import utils.AutoSignIn;
import view.forms.RoomForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public class RoomsTableView extends GenericTableView<Room> {

    private RoomAPI api;
    private List<Room> roomList = new ArrayList<>();

    public RoomsTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        Room r = roomList.get(index);
        api.delete(r,response -> reloadDataFromServer());
        reloadData();
    }

    @Override
    protected UIFormView<Room> onView(int index) {
        Room r = roomList.get(index);
        return new RoomForm(r,this);
    }

    @Override
    protected UIFormView<Room> onInsert() {
        return new RoomForm(null,this);
    }

    @Override
    protected Class<Room> classType() {
        return Room.class;
    }

    @Override
    public Collection<? extends Room> dataSource() {
        return roomList;
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new RoomAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    @Override
    public void callback(Room value) {
        super.callback(value);
        api.upsert(value, response -> reloadDataFromServer());
    }

    private void reloadDataFromServer(){
        api.readAll(new Room(AutoSignIn.HOSPITAL_ID,0,0),(response, items) -> {
            if(response.isOK()) {
                roomList.clear();
                roomList.addAll(items);
                reloadData();
            }
        });
    }
}
