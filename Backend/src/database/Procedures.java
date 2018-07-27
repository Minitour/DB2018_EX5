package database;

/**
 * Created By Tony on 19/07/2018
 */
public class Procedures {

    public static class Account {
        public static final String get = "get_account";
        public static final String insert = "insert_account";
        public static final String update = "update_account";
    }

    public static class Session {
        public static final String get = "get_sessions";
        public static final String insert = "insert_session";
    }

    public static class Profile {
        public static final String get = "get_profile";
        public static final String insert = "insert_profile";
    }

    public static class Rooms {
        public static final String get = "get_rooms";
        public static final String update = "update_room";
        public static final String delete = "delete_room";
        public static final String insert = "insert_room";
    }

    public static class Department {
        public static String get = "get_department";
        public static String update = "update_department";
        public static String insert = "insert_department";
        public static String delete = "delete_department";
    }

    public static class Shift {
        public static String get = "get_shift";
        public static String update = "update_shift";
        public static String delete = "delete_shift";
        public static String insert = "insert_shift";
    }

    public static class Hospitalized {
        public static String get = "get_hospitalized";
        public static String update = "update_hospitalized";
        public static String delete = "delete_hospitalizations";
        public static String insert = "insert_hospitalized";
    }

    public static class Patients {
        public static String get = "get_patients";
        public static String update = "update_patient";
        public static String delete = "delete_patient";
        public static String insert = "insert_patient";
    }

    public static class Checks {
        public static String get = "get_checks";
        public static String insert = "insert_check";
    }

    public static class Doctor {
        public static String get = "get_doctor";
        public static String update = "update_doctor";
        public static String delete = "delete_doctor";
        public static String insert = "insert_doctor";
    }

    public static class Hospital {
        public static String get = "get_hospital";
        public static String update = "update_hospital";
        public static String delete = "delete_hospital";
        public static String insert = "insert_hospital";
    }

}
