package network;

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

        public static String login(){
            return getAddress() + "/login";
        }
        public static String getAccounts() { return getAddress() + "/getAccounts"; }
    }
    public static class Codes{
        public static final int SUCCESS=200;
        public static final int MISSING_PARAMETERS=401;

    }

}
