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
    private

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

    public List<Query_3> get_query_3() {

        try {

            getQueryInstance();



            return null;

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Query_4> get_query_4() {
        try {

            getQueryInstance();

            List<HospitalJoinPersonJoinDoctor> joinList = queriesAccess.query4();

            List<Query_4> objectsList = new ArrayList<>();

            joinList.forEach(record -> {
                objectsList.add(new Query_4(record.left().left().getName(), record.left().right().getID(), record.left().right().getFirstName(), record.left().right().getSurName()));
            });

            return objectsList;

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Query_5> get_query_5(String doctorID) {
        return null;
    }

    public List<Query_6> get_query_6() {
        return null;
    }

    public List<Query_7> get_query_7() {
        return null;
    }

    public List<Person> get_query_8() {
        return null;
    }

    public List<Query_9> get_query_9() {
        return null;
    }

    public List<Query_11> get_query_11() {
        return null;
    }



}
