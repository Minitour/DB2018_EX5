package controller.master;

import ui.UIView;
import view.CardView;
import view.forms.MedicalEventTypeInDepartmentForm;
import view.special.AccountView;
import view.special.DashboardView;
import view.tables.*;

import java.util.ResourceBundle;

/**
 * Created By Tony on 25/07/2018
 */
public class AdminMaster extends MasterMenuController {

    UIView[] views = {
            new DashboardView(),
            new PatientsTableView(true,true,true),
            new RoomsTableView(true,true,true),
            new DoctorsTableView(true,true,true),
            new DoctorVacationTableView(true,false,true),
            new HospitalizationTableView(false,false,false),
            new ChecksTableView(false,false,false),
            new MedicalEventTypeInDepTableView(true,false,true),
            new DepartmentTableView(true,true,true),
            new CardView(new AccountView())
    };

    public AdminMaster() {
        onListItemChanged(0);
    }

    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public MenuItem[] itemsForMenu() {

        return new MenuItem[]{
                new MenuItem("Dashboard","/resources/img/ic_dashboard.png"),
                new MenuItem("Patients","/resources/img/ic_patients.png"),
                new MenuItem("Rooms", "resources/img/ic_room.png"),
                new MenuItem("Doctors","/resources/img/ic_doctor.png"),
                new MenuItem("Vacations", "/resources/img/ic_vacation.png"),
                new MenuItem("Hospitalizations", "/resources/img/ic_hospitalizations.png"),
                new MenuItem("Checks", "/resources/img/ic_checks2.png"),
                new MenuItem("Events In Department", "/resources/img/ic_events_in_dep.png"),
                new MenuItem("Departments", "/resources/img/ic_department.png"),
                new MenuItem("Account", "/resources/img/ic_account.png"),
        };
    }

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);
        //setNavBar("#3F51B5",true);
    }
}
