package controller.master;

import ui.UIView;

/**
 * Created By Tony on 25/07/2018
 */
public class AdminMaster extends MasterMenuController {

    UIView[] views = {};

    @Override
    public UIView viewForIndexAt(int index) {
        return null;
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
