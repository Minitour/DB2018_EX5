package controller.master;

import model.Account;
import network.api.AccountAPI;
import network.generic.GenericAPI;
import ui.UIView;
import utils.Response;
import view.CardView;
import view.forms.AccountForm;
import view.generic.UIFormView;
import view.special.AccountView;
import view.tables.HospitalizationTableView;
import view.tables.PatientsTableView;
import view.tables.RoomsTableView;

/**
 * Created By Tony on 25/07/2018
 */
public class SecretaryMaster extends MasterMenuController implements UIFormView.OnFinish<Account>{

    UIView[] views = {
            new PatientsTableView(true,true,true),
            new RoomsTableView(false,false,false),
            new HospitalizationTableView(false,true,true),
            new CardView(new AccountForm(null,this,false)),
            new CardView(new AccountView())
    };

    public SecretaryMaster() {
        onListItemChanged(0);
    }

    @Override
    public UIView viewForIndexAt(int index) {
        return views[index];
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{"Patients","Rooms","Hospitalization","Create Account","Account"};
    }

    @Override
    public void callback(Account value) {
        UIFormView<Account> formView = (UIFormView<Account>) ((CardView) views[3]).getContentView();
        new AccountAPI().upsert(value, response -> {
            if(response.isOK()){
                formView.reset();
            }
        });


    }
}
