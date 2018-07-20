package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.Payment;

/**
 * Created By Tony on 19/07/2018
 */
public class PaymentAccess extends GenericAccess<Payment> {
    public PaymentAccess() { super(Payment.class,"Paymentes"); }
    public PaymentAccess(Database db) { super(Payment.class,"Paymentes",db); }
}
