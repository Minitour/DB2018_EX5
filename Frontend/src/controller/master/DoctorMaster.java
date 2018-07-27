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

import java.util.ResourceBundle;

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
    public String[] itemsForMenu() {
        return new String[]{
                "Patients",
                "Rooms",
                "Hospitalizations",
                "Checks",
                "Working Shifts",
                "All Shifts",
                "Account"
        };
    }
}
