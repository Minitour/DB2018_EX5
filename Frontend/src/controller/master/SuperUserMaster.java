package controller.master;

import ui.UIView;
import view.CardView;
import view.forms.DepartmentForm;
import view.forms.HospitalForm;
import view.special.AccountView;
import view.special.DashboardView;
import view.tables.AccountTableView;
import view.tables.HospitalTableView;
import view.tables.MedicalEventTableView;

/**
 * Created By Tony on 25/07/2018
 */
public class SuperUserMaster extends MasterMenuController {

    UIView[] views = {
            new HospitalTableView(true,true,true),
            new AccountTableView(true,true,true),
            new MedicalEventTableView(true,true,true),
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
                "Hospitals", //create hospitals
                "Users", // create admins
                "Medical Events",
                "Account"
        };
    }
}
