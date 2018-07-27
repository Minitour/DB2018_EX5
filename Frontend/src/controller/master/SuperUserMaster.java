package controller.master;

import ui.UIView;
import view.forms.DepartmentForm;
import view.forms.HospitalForm;

/**
 * Created By Tony on 25/07/2018
 */
public class SuperUserMaster extends MasterMenuController {

    UIView[] views = {
        //new DepartmentForm(null,null)
    };
    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
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
