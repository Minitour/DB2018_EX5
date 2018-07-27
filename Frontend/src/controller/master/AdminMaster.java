package controller.master;

import ui.UIView;
import view.forms.AccountForm;

/**
 * Created By Tony on 25/07/2018
 */
public class AdminMaster extends MasterMenuController {

    UIView[] views = {
            new AccountForm(null,null)
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
