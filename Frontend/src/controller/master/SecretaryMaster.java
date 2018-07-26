package controller.master;

import ui.UIView;
import view.AccountView;
import view.tables.PatientsTableView;

/**
 * Created By Tony on 25/07/2018
 */
public class SecretaryMaster extends MasterMenuController {

    UIView[] views = {
            new PatientsTableView(true,true,true),
            null,
            null,
            new AccountView()
    };

    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{"Patients","Rooms","Hospitalization","Account"};
    }
}
