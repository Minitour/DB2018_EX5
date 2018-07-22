package controller;

import com.google.gson.JsonObject;
import model.Session;
import utils.GenericController;

public class DoctorsController extends GenericController {

    @Override
    public Object read(JsonObject body, Session session) {

        JsonObject params = parameters(body);
        require(params);

        String doctorID = params.get("ID").getAsString();

    }

    @Override
    public Object readAll(JsonObject body, Session session) {
        return super.readAll(body, session);
    }

    @Override
    public Object upsert(JsonObject body, Session session) {
        return super.upsert(body, session);
    }

    @Override
    public Object delete(JsonObject body, Session session) {
        return super.delete(body, session);
    }
}
