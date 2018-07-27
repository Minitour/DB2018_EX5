package controller.master;

import ui.UIView;
import view.special.AccountView;
import view.special.ProfileView;

/**
 * Created By Tony on 25/07/2018
 */
public class PatientMaster extends MasterMenuController {

    private UIView[] views = {
            new ProfileView(),
            new AccountView()
    };

    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{"Profile","Account"};
    }

}
