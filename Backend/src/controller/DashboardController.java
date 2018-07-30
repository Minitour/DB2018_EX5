package controller;

import com.google.gson.JsonObject;
import database.data_access.PersonAccess;
import database.data_access.QueriesAccess;
import model.DashboardModel;
import model.Session;
import spark.Request;
import spark.Response;
import utils.JSONResponse;
import utils.RESTRoute;

public class DashboardController implements RESTRoute {


    //TODO: validate session + permissions


    @Override
    public Object handle(Request request, Response response, JsonObject body, Session session) throws Exception {

        try(QueriesAccess query_db = new QueriesAccess();
            PersonAccess personAccess = new PersonAccess(query_db)){

            validateSession(session,query_db,false);


            if(!hasPermission("io.hospital.dashboard.read",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            DashboardModel dashboard = new DashboardModel();;
            switch (session.getRole()) {
                case 5:
                    //admin dashboard

                    // assign the relevant components (queries) to the admin dashboard
                    dashboard.setQuery2_result(query_db.query2());
                    // dashboardModel.setQuery5_result(query_db.query5(null));
                    dashboard.setQuery8_result(query_db.query8());
                    dashboard.setQuery9_result(query_db.query9(session.getHospitalId()));
                    dashboard.setQuery6_result(query_db.query6(session.getHospitalId()));
                    dashboard.setQuery11_result(query_db.query11(personAccess.getById(session.ACCOUNT_ID,null).get(0).getID()));
                    dashboard.setQuery13_result(query_db.query13(session.getHospitalId()));
                    dashboard.setQuery14_result(query_db.query14(session.getHospitalId()));
                    dashboard.setQuery15_result(query_db.query15(session.getHospitalId()));

                    break;

                case 6:
                    //super user dashboard

                    // assign the relevant components (queries) to the super user dashboard
                    dashboard.setQuery3_result(query_db.query3());
                    dashboard.setQuery4_result(query_db.query4());
                    dashboard.setQuery7A_result(query_db.query7A());
                    dashboard.setQuery7B_result(query_db.query7B());
                    dashboard.setQuery13A_result(query_db.query13A());
                    break;
            }
            return JSONResponse
                    .SUCCESS()
                    .data(dashboard);

        }catch (Exception e){
            return JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }




}
