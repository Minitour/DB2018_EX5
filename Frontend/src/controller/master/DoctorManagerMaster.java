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
//        return new String[]{
//                "Patients",
//                "Rooms",
//                "Hospitalizations",
//                "Checks",
//                "Working Shifts",
//                "Shifts",
//                "Doctors",
//                "Department",
//                "Account"
//        };

        return new MenuItem[]{
                new MenuItem("Patients",""),
                new MenuItem("Rooms",""),
                new MenuItem("Hospitalizations",""),
                new MenuItem("Checks",""),
                new MenuItem("Working Shifts",""),
                new MenuItem("Shifts",""),
                new MenuItem("Doctors",""),
                new MenuItem("Department",""),
                new MenuItem("Account",""),
        };
    }
}
