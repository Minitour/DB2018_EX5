package model.dashboard;

import controller.DashboardController;
import model.Person;

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



    DashboardController controller;

    void controllerInstance() {
        if (controller == null) {
            try {
                controller = new DashboardController();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void get_query_2_list() {
        controllerInstance();
        query_2_list = controller.get_query_2();
    }

    private void get_query_3_list() {
        controllerInstance();
        query_3_list = controller.get_query_3();
    }

    private void get_query_4_list() {
        controllerInstance();
        query_4_list = controller.get_query_4();
    }

    private void get_query_5_list() {
        controllerInstance();
        query_5_list = controller.get_query_5();
    }

    private void get_query_6_list() {
        controllerInstance();
        query_6_list = controller.get_query_6();
    }

    private void get_query_7_list() {
        controllerInstance();
        query_7_list = controller.get_query_7();
    }

    private void get_query_8_list() {
        controllerInstance();
        query_8_list = controller.get_query_8();
    }

    private void get_query_9_list() {
        controllerInstance();
        query_9_list = controller.get_query_9();
    }

    private void get_query_11_list() {
        controllerInstance();
        query_11_list = controller.get_query_11();
    }




}
