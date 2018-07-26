package view.forms;

import model.Payment;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class PaymentForm extends UIFormView<Payment> {
    public PaymentForm(Payment existingValue, OnFinish<Payment> callback) {
        super(Payment.class, existingValue, callback);
    }
}
