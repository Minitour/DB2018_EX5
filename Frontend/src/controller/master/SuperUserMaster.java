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
                new MenuItem("Dashboard", "/resources/img/ic_dashboard.png"),
                new MenuItem("Hospitals", "/resources/img/ic_hospital.png"),
                new MenuItem("Users", "/resources/img/ic_accounts.png"),
                new MenuItem("Profiles", "/resources/img/ic_users.png"),
                new MenuItem("Medical Events", "/resources/img/ic_medical_event.png"),
                new MenuItem("Medical Event Types", "/resources/img/ic_medical_event_type.png"),
                new MenuItem("Shifts", "/resources/img/ic_shifts.png"),
                new MenuItem("Payments", "/resources/img/ic_payment.png"),
                new MenuItem("Account", "/resources/img/ic_account.png")
        };
    }
}
