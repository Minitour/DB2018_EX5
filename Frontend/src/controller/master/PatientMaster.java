package controller.master;

import ui.UIView;
import view.AccountView;
import view.ProfileView;
import view.forms.PersonForm;

/**
 * Created By Tony on 25/07/2018
 */
public class PatientMaster extends MasterMenuController {

    //TODO: add views
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
