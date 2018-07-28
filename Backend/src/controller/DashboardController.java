package controller;

import com.google.gson.JsonObject;
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

        try(QueriesAccess query_db = new QueriesAccess()){

            validateSession(session,query_db,false);

            if(!hasPermission("io.hospital.dashboard.read",session))
                return JSONResponse.FAILURE().message("Access Denied.");

            DashboardModel dashboardModel = new DashboardModel();

            //populate data
            dashboardModel.setQuery2_result(query_db.query2());
            dashboardModel.setQuery3_result(query_db.query3());
            dashboardModel.setQuery4_result(query_db.query4());
//            dashboardModel.setQuery5_result(query_db.query5(null));
            dashboardModel.setQuery6_result(query_db.query6());
            dashboardModel.setQuery7A_result(query_db.query7A());
            dashboardModel.setQuery7B_result(query_db.query7B());
            dashboardModel.setQuery8_result(query_db.query8());
            dashboardModel.setQuery9_result(query_db.query9());
            dashboardModel.setQuery11_result(query_db.query8());

            return JSONResponse
                    .SUCCESS();
        }catch (Exception e){
            JSONResponse
                    .FAILURE()
                    .message(e.getMessage());
        }
    }




}
