package controller.master;

import ui.UIView;
import view.AccountView;
import view.tables.HospitalizationTableView;
import view.tables.PatientsTableView;
import view.tables.RoomsTableView;

/**
 * Created By Tony on 25/07/2018
 */
public class SecretaryMaster extends MasterMenuController {

    UIView[] views = {
            new PatientsTableView(true,true,true),
            new RoomsTableView(false,false,false),
            new HospitalizationTableView(false,false,false),
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
