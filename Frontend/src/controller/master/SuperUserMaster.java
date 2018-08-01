package controller.master;

import ui.UIView;
import view.CardView;
import view.special.AccountView;
import view.special.SuperDashboardView;
import view.tables.*;

/**
 * Created By Tony on 25/07/2018
 */
public class SuperUserMaster extends MasterMenuController {

    UIView[] views = {
            new SuperDashboardView(),
            new HospitalTableView(true,true,true),
            new AccountTableView(true,true,true),
            new ProfileTableView(true,true,true),
            new MedicalEventTableView(true,true,true),
            new MedicalEventTypeTableView(true,true,true),
            new ShiftTableView(true,true,true),
            new PaymentTableView(true,true,true),
            new CardView(new AccountView())
    };

    public SuperUserMaster() {
        onListItemChanged(0);
    }

    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public MenuItem[] itemsForMenu() {
        return new MenuItem[]{
                new MenuItem("Dashboard",""),
                new MenuItem("Hospitals",""),
                new MenuItem("Users",""),
                new MenuItem("Profiles",""),
                new MenuItem("Medical Events",""),
                new MenuItem("Medical Event Types",""),
                new MenuItem("Shifts",""),
                new MenuItem("Payments",""),
                new MenuItem("Account","")
        };
    }
}
