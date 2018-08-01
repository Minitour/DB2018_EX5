package controller.master;

import ui.UIView;
import view.CardView;
import view.special.AccountView;
import view.special.ShiftsView;
import view.special.WorkingShiftsView;
import view.tables.ChecksTableView;
import view.tables.HospitalizationTableView;
import view.tables.PatientsTableView;
import view.tables.RoomsTableView;

/**
 * Created By Tony on 25/07/2018
 */
public class DoctorMaster extends MasterMenuController {

    UIView[] views = {
            new PatientsTableView(true,true,false),
            new RoomsTableView(false,false,false),
            new HospitalizationTableView(true,true,true),
            new ChecksTableView(true,true,true),
            new WorkingShiftsView(),
            new ShiftsView(),
            new CardView(new AccountView())
    };

    public DoctorMaster() {
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
                new MenuItem("Rooms", "/resources/img/ic_room.png"),
                new MenuItem("Hospitalizations", "/resources/img/ic_hospitalizations.png"),
                new MenuItem("Checks", "/resources/img/ic_checks2.png"),
                new MenuItem("Working Shifts", "/resources/img/ic_workinshifts.png"),
                new MenuItem("All Shifts", "/resources/img/ic_shifts.png"),
                new MenuItem("Account", "/resources/img/ic_account.png")
        };
    }
}
