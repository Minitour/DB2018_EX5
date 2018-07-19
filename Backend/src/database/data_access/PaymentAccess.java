package database.data_access;

import database.GenericAccess;
import model.Payment;

/**
 * Created By Tony on 19/07/2018
 */
public class PaymentAccess extends GenericAccess<Payment> {
    public PaymentAccess() { super(Payment.class,"Paymentes"); }
}
