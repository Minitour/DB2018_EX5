package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.data_access.PaymentAccess;
import model.Payment;
import model.Session;
import utils.GenericController;
import utils.JSONResponse;

import java.util.List;

/**
 * Created By Tony on 31/07/2018
 */
public class PaymentController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        int id = params.get("serialNumber").getAsInt();

        try(PaymentAccess payment_db = new PaymentAccess()) {

            validateSession(session, payment_db, false);

            if (!hasPermission("io.hospital.payment.read", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<Payment> payments = payment_db.getById(id);
            if (payments.size() != 1)
                return JSONResponse.FAILURE().message("Not found.");

            return JSONResponse
                    .SUCCESS()
                    .data(payments.get(0));

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object readAll(JsonObject body, Session session) {
        try(PaymentAccess access = new PaymentAccess()) {

            validateSession(session, access, false);

            if (!hasPermission("io.hospital.payment.read_all", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            List<Payment> payments = access.getAll();
            JSONResponse<List<Payment>> jsonResponse = JSONResponse.SUCCESS();
            jsonResponse.data(payments);

            return jsonResponse;

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object delete(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);

        int serialNumber = params.get("serialNumber").getAsInt();

        try(PaymentAccess paymentAccess = new PaymentAccess()) {

            validateSession(session, paymentAccess, false);

            if (!hasPermission("io.hospital.payment.delete", session))
                return JSONResponse.FAILURE().message("Access Denied.");

            paymentAccess.delete(serialNumber);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }

    @Override
    public Object upsert(JsonObject body, Session session) {
        JsonObject params = parameters(body);
        require(params);
        Gson gson = new Gson();

        Payment payment = gson.fromJson(params,Payment.class);

        try(PaymentAccess paymentAccess= new PaymentAccess()) {

            //validate session
            validateSession(session,paymentAccess,false);

            //check permissions
            boolean isOk = hasPermission("io.hospital.payment.upsert",session);

            if(!isOk)
                return JSONResponse.FAILURE().message("Access Denied");

            paymentAccess.upsert(payment);

            return JSONResponse.SUCCESS();

        } catch (Exception e) {
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }
}
