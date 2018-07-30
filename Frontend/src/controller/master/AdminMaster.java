package controller.master;

import ui.UIView;
import view.CardView;
import view.forms.MedicalEventTypeInDepartmentForm;
import view.special.AccountView;
import view.special.DashboardView;
import view.tables.*;

/**
 * Created By Tony on 25/07/2018
 */
public class AdminMaster extends MasterMenuController {

    UIView[] views = {
            new DashboardView(),
            new PatientsTableView(true,true,false),
            new RoomsTableView(true,true,true),
            new HospitalizationTableView(false,false,false),
            new ChecksTableView(true,true,true),
            new MedicalEventTypeInDepTableView(true,true,true),
            new DepartmentTableView(true,true,true),
            new CardView(new AccountView())
    };

    public AdminMaster() {
        onListItemChanged(0);
    }

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
                "Events In Department",
                "Departments",
                "Account"
        };
    }
}
