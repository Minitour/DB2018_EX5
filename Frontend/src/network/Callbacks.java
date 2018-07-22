package network;

import com.google.gson.JsonObject;

/**
 * Created By Tony on 14/02/2018
 */
public final class Callbacks {

    private Callbacks(){}

    @FunctionalInterface
    interface Inner{
        void make(JsonObject json, Exception exception);
    }

    /**
     * General callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface General{
        void make(Response response, Exception exception);
    }



}
