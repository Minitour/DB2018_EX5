package controller.master;

import ui.UIView;

/**
 * Created By Tony on 25/07/2018
 */
public class SecretaryMaster extends MasterMenuController {
    @Override
    public UIView viewForIndexAt(int index) {
        return null;
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{"Patients","Rooms","Hospitalization","Account"};
    }
}
