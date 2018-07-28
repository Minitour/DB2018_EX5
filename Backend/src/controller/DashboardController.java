package controller;

import com.google.gson.JsonObject;
import database.DashboardDB;
import database.Database;
import database.data_access.QueriesAccess;
import model.Person;
import model.Session;
import model.dashboard.*;
import model.join.HospitalJoinPerson;
import model.join.HospitalJoinPersonJoinDoctor;
import model.join.PersonJoinCheckedBy;
import spark.Request;
import spark.Response;
import utils.GenericController;
import utils.RESTRoute;

import java.util.ArrayList;
import java.util.List;

public class DashboardController implements RESTRoute {


    //TODO: validate session + permissions

    private QueriesAccess queriesAccess;

    void getQueryInstance() {
        if (queriesAccess == null) {
            try{
                queriesAccess = new QueriesAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    DashboardDB DashboardController() {
        DashboardDB db = new DashboardDB();
        return db;
    }


    @Override
    public Object handle(Request request, Response response, JsonObject body, Session session) throws Exception {
        return null;
    }


    public void add_to_query_2() {

        try{

            getQueryInstance();

            DashboardDB temp = DashboardController();

            // get the the object we wanted
            List<HospitalJoinPerson> joinList = queriesAccess.query2();

            // create a new list for the controller
            List<Query_2> objectsList = new ArrayList<>();

            // creating the objects with the correct model and add to the list
            joinList.forEach(record -> {
                objectsList.add(new Query_2(record.right().getID(), record.right().getFirstName(), record.right().getSurName(), record.left().getName()));
            });

            temp.add_to_query_2_list(objectsList);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void add_to_query_3() {


    }

    public void add_to_query_4() {

    }

    public void add_to_query_5(String doctorID) {

    }

    public void add_to_query_6() {

    }

    public void add_to_query_7() {

    }

    public void add_to_query_8() {

    }

    public void add_to_query_9() {

    }

    public void add_to_query_11() {


    }



}
