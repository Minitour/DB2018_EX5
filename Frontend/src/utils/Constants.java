package utils;

import utils.Config;

/**
 * Created By Tony on 14/02/2018
 */
final public class Constants {

    /**
     * Protocol
     */
    public final static String protocol = Config.config.get("protocol").getAsString();

    /**
     * Address
     */
    public final static String address = Config.config.get("address").getAsString();

    /**
     * Port
     */
    public final static String port = Config.config.get("port").getAsString();

    /**
     * @return full path
     */
    public final static String getAddress(){
        return protocol + "://" + address + ":" + port;
    }

    /**
     * Routes class, contains all API routes.
     */
    public static class Routes {

        public static String checkedBy() { return getAddress() + "/checkedBy"; }

        public static String department() { return getAddress() + "/department"; }

        public static String doctor() { return getAddress() + "/doctor"; }

        public static String event() { return getAddress() + "/event"; }

        public static String eventInDep() { return getAddress() + "/eventInDep"; }

        public static String eventTypes() { return getAddress() + "/eventTypes"; }

        public static String hospital() { return getAddress() + "/hospital"; }

        public static String hospitalized() { return getAddress() + "/hospitalized"; }

        public static String profile() { return getAddress() + "/profile"; }

        public static String payment() { return getAddress() + "/payment"; }

        public static String room() { return getAddress() + "/room"; }

        public static String shift() { return getAddress() + "/shift"; }

        public static String vacation() { return getAddress() + "/vacation"; }

        public static String workInShift() { return getAddress() + "/workInShift"; }

        public static String login(){ return getAddress() + "/login"; }

        public static String updatePassword(){ return getAddress() + "/updatePassword"; }

        public static String patients() { return getAddress() + "/patient"; }
    }
    public static class Codes{
        public static final int SUCCESS=200;
        public static final int MISSING_PARAMETERS=401;

    }

}
