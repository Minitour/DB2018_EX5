package network.api;

import model.Payment;
import network.generic.GenericAPI;
import utils.Constants;

public class PaymentAPI extends GenericAPI<Payment> {
    /**
     * @param url the base endpoint url.
     */
    public PaymentAPI() { super(Constants.Routes.payment()); }
}
