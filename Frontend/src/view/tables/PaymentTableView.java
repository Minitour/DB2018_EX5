package view.tables;

import model.Payment;
import network.api.PaymentAPI;
import view.forms.PaymentForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentTableView extends GenericTableView<Payment> {

    PaymentAPI api ;
    List<Payment> payments = new ArrayList<>();

    public PaymentTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        Payment vacation = payments.remove(index);
        api.delete(vacation,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<Payment> onView(int index) {
        Payment vacation = payments.get(index);
        return new PaymentForm(vacation,this);
    }

    @Override
    protected UIFormView<Payment> onInsert() {
        return new PaymentForm(null, this);
    }

    @Override
    protected Class<Payment> classType() {
        return Payment.class;
    }


    @Override
    public Collection<? extends Payment> dataSource() {
        return payments;
    }

    @Override
    public void callback(Payment value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, response -> reloadDataFromServer());
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new PaymentAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            payments.clear();
            payments.addAll(items);
            reloadData();
        });
    }
}
