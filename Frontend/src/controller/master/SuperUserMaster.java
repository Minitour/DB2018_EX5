package controller.master;

import ui.UIView;
import view.forms.DepartmentForm;
import view.forms.HospitalForm;
import view.special.AccountView;
import view.tables.AccountTableView;
import view.tables.HospitalTableView;

/**
 * Created By Tony on 25/07/2018
 */
public class SuperUserMaster extends MasterMenuController {

    UIView[] views = {
            new HospitalTableView(true,true,true),
            new AccountTableView(true,true,true),
            new AccountView()
    };
    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{
                "Hospitals", //create hospitals
                "Users", // create admins
                "Account"
        };
    }
}
