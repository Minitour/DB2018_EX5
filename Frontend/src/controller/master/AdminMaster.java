package controller.master;

import ui.UIView;
import view.special.AccountView;
import view.tables.*;

/**
 * Created By Tony on 25/07/2018
 */
public class AdminMaster extends MasterMenuController {

    UIView[] views = {
            null, //TODO: add dashboard
            new PatientsTableView(true,true,true),
            new RoomsTableView(true,true,true),
            new HospitalizationTableView(true,true,true),
            new ChecksTableView(true,true,true),
            new ShiftTableView(true,true,true),
            new AccountView()
    };

    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{
                "Dashboard",
                "Patients",
                "Rooms",
                "Hospitalizations",
                "Checks",
                "Shifts",
                "Account"
        };
    }
}
