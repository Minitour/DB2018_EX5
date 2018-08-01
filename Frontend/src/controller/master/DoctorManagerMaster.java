package controller.master;

import ui.UIView;
import view.CardView;
import view.special.AccountView;
import view.special.ShiftsView;
import view.special.WorkingShiftsView;
import view.tables.*;

/**
 * Created By Tony on 25/07/2018
 */
public class DoctorManagerMaster extends MasterMenuController {

    UIView[] views = {
            new PatientsTableView(false,true,false),
            new RoomsTableView(false,false,false),
            new HospitalizationTableView(true,true,true),
            new ChecksTableView(true,true,true),
            new WorkInShiftTableView(true,true,true),
            new ShiftTableView(false,false,false),
            new DoctorsTableView(false,true,false),
            new DepartmentTableView(false,false,false),
            new CardView(new AccountView())
    };

    public DoctorManagerMaster() {
        onListItemChanged(0);
    }

    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public MenuItem[] itemsForMenu() {

        return new MenuItem[]{
                new MenuItem("Patients", "/resources/img/ic_patients.png"),
                new MenuItem("Rooms","/resources/img/ic_room.png"),
                new MenuItem("Hospitalizations", "/resources/img/ic_hospitalizations.png"),
                new MenuItem("Checks", "/resources/img/ic_checks2.png"),
                new MenuItem("Working Shifts", "/resources/img/ic_workinshifts.png"),
                new MenuItem("Shifts", "resources/img/ic_shifts.png"),
                new MenuItem("Doctors", "/resources/img/ic_doctor.png"),
                new MenuItem("Department", "/resources/img/ic_department.png"),
                new MenuItem("Account", "/resources/img/ic_account.png"),
        };
    }
}
