package controller.master;

import ui.UIView;

/**
 * Created By Tony on 25/07/2018
 */
public class SuperUserMaster extends MasterMenuController {
    @Override
    public UIView viewForIndexAt(int index) {
        return null;
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{
                "Hospitals", //create hospitals
                "Admins", // create admins
                "Account"
        };
    }
}
