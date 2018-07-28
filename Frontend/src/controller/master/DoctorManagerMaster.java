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
            new PatientsTableView(true,true,false),
            new RoomsTableView(false,false,false),
            new HospitalizationTableView(true,true,true),
            new ChecksTableView(true,true,true),
            new WorkingShiftsView(),
            new ShiftTableView(true,true,true),
            new DoctorsTableView(false,true,true),
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
    public String[] itemsForMenu() {
        return new String[]{
                "Patients",
                "Rooms",
                "Hospitalizations",
                "Checks",
                "Working Shifts",
                "Manage Shifts",
                "Doctors",
                "Department",
                "Account"
        };
    }
}
