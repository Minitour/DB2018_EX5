package controller.master;

import model.Account;
import network.api.AccountAPI;
import ui.UIView;
import view.CardView;
import view.DialogView;
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
    public MenuItem[] itemsForMenu() {
        return new MenuItem[]{
                new MenuItem("Patients",""),
                new MenuItem("Rooms",""),
                new MenuItem("Hospitalization",""),
                new MenuItem("Create Account",""),
                new MenuItem("Account","")
        };
    }

    @Override
    public void callback(Account value) {
        UIFormView<Account> formView = (UIFormView<Account>) ((CardView) views[3]).getContentView();
        new AccountAPI().upsert(value, response -> {
            if(response.isOK()){
                new AccountAPI().read(value, (response1, object) -> {
                    DialogView dialogView= makeDialog("Success","Created new account with ID ["+object.getACCOUNT_ID()+"]");
                    dialogView.setPostiveEventHandler(event -> dialogView.close());
                    dialogView.show(this.view);
                    formView.reset();
                });
            }
        });


    }
}
