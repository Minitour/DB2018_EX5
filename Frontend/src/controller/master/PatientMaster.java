package controller.master;

import ui.UIView;
import view.CardView;
import view.special.AccountView;
import view.special.ProfileView;

import java.util.ResourceBundle;

/**
 * Created By Tony on 25/07/2018
 */
public class PatientMaster extends MasterMenuController {

    private UIView[] views = {
            new CardView(new ProfileView()),
            new CardView(new AccountView())
    };


    public PatientMaster() {
        onListItemChanged(0);
    }

    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public MenuItem[] itemsForMenu() {
        return new MenuItem[]{
                new MenuItem("Profile",""),
                new MenuItem("Account","")
        };
    }

}
