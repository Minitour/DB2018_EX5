package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import javafx.scene.control.TableColumn;
import model.Department;
import model.Hospital;
import model.Room;
import network.api.DepartmentAPI;
import network.api.HospitalAPI;
import network.api.PatientsAPI;
import network.api.RoomAPI;
import network.generic.GenericAPI;
import ui.UITableView;
import utils.AutoSignIn;
import utils.Response;
import view.forms.RoomForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.*;

/**
 * Created By Tony on 26/07/2018
 */
public class RoomsTableView extends GenericTableView<Room> {

    private RoomAPI api;
    private DepartmentAPI departmentAPI;
    private HospitalAPI hospitalAPI;
    private List<Room> roomList = new ArrayList<>();

    private Map<String,Department> departmentMap;
    private Map<Integer, Hospital> hospitalMap;

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

        departmentAPI = new DepartmentAPI();
        hospitalAPI = new HospitalAPI();
        departmentMap = new HashMap<>();
        hospitalMap = new HashMap<>();

        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    @Override
    public TableColumnValue<Room> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return object -> hospitalMap.get(object.getHospitalID()).getName();
            case 1:
                return object -> departmentMap.get(object.getHospitalID() + "_" + object.getDepartmentID()).getDepartmentName();
                default: return super.cellValueForColumnAt(index);
        }
    }

    @Override
    public void callback(Room value) {
        super.callback(value);
        api.upsert(value, response -> {

            JFXSnackbar bar = new JFXSnackbar(this);
            bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Success" : "Failed, response message: " + response.getMessage()));
            reloadDataFromServer();

        });
    }

    private void reloadDataFromServer(){
        api.readAll(new Room(AutoSignIn.HOSPITAL_ID,0,0),(response, items) -> {
            hospitalAPI.readAll((response2, items12) -> {
                for (Hospital hospital : items12){
                    hospitalMap.put(hospital.getHospitalID(),hospital);
                }

                departmentAPI.readAll(new Department(AutoSignIn.HOSPITAL_ID), (response1, items1) -> {

                    for (Department department : items1) {
                        departmentMap.put(department.getHospitalID() + "_" + department.getDepartmentID(),department);
                    }

                    if(response.isOK()) {
                        roomList.clear();
                        roomList.addAll(items);
                        reloadData();
                    }
                });
            });


        });
    }
}
