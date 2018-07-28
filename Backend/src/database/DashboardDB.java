package database;

import controller.DashboardController;
import model.Person;
import model.dashboard.*;

import java.util.List;

public class DashboardDB {

    public static List<Query_2> query_2_list;
    public static List<Query_3> query_3_list;
    public static List<Query_4> query_4_list;
    public static List<Query_5> query_5_list;
    public static List<Query_6> query_6_list;
    public static List<Query_7> query_7_list;
    public static List<Person> query_8_list;
    public static List<Query_9> query_9_list;
    public static List<Query_11> query_11_list;


    public void add_to_query_2_list(List<Query_2> list) {
        query_2_list = list;
    }

    public void add_to_query_3_list(List<Query_3> list) {
        query_3_list = list;
    }

    public void add_to_query_4_list(List<Query_4> list) {
        query_4_list = list;
    }

    public void add_to_query_5_list(List<Query_5> list) {
        query_5_list = list;
    }

    public void add_to_query_6_list(List<Query_6> list) {
        query_6_list = list;
    }





}
