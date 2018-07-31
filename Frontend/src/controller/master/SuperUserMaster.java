package controller.master;

import ui.UIView;
import view.CardView;
import view.forms.DepartmentForm;
import view.forms.HospitalForm;
import view.special.AccountView;
import view.special.DashboardView;
import view.special.SuperDashboardView;
import view.tables.*;

/**
 * Created By Tony on 25/07/2018
 */
public class SuperUserMaster extends MasterMenuController {

    UIView[] views = {
            new SuperDashboardView(),
            new HospitalTableView(true,true,true),
            new AccountTableView(true,true,true),
            new ProfileTableView(true,true,true),
            new MedicalEventTableView(true,true,true),
            new MedicalEventTypeTableView(true,true,true),
            new ShiftTableView(true,true,true),
            new PaymentTableView(true,true,true),
            new CardView(new AccountView())
    };

    public SuperUserMaster() {
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
                "Hospitals", //create hospitals
                "Users", // create admins
                "Profiles",
                "Medical Events",
                "Medical Event Types",
                "Shifts",
                "Payments",
                "Account"
        };
    }
}
