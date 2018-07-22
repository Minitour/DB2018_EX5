package network.generic;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import network.AutoSignIn;
import network.Response;

import java.util.List;

/**
 * Created By Tony on 23/07/2018
 */
public class GenericAPI<T> {

    private static final String DELETE = "delete";
    private static final String READ_ALL = "read_all";
    private static final String READ = "read";
    private static final String UPSERT = "upsert";

    protected final String url;

    private final Gson gson = new Gson();

    public GenericAPI(String url){
        this.url = url;
    }

    public void read(T object, Read<T> callback){
        do_read(callback,object);
    }

    public void readAll(ReadAll<T> callback){
        do_read_all(callback);
    }

    public void upsert(T object, Upsert<T> callback){
        do_upsert(callback,object);
    }

    public void delete(T object, Delete<T> callback){
        do_delete(callback,object);
    }


    private void do_read(final Read<T>  callback, T input){
        JsonObject body = new JsonObject();
        build(body,READ,input);

        SharedInstance.main.makeRequest(url, null, body, (json, exception) -> {
            Response r = new Response(json);
            r.setException(exception);

            if(exception == null) {
                JsonElement data = json.get("data");
                T obj = gson.fromJson(data, (Class<T>) input.getClass());
                callback.execute(r,obj);
            }else {
                callback.execute(r,null);
            }
        });

    }

    private void do_read_all(ReadAll<T>  callback){
        JsonObject body = new JsonObject();
        build(body,READ_ALL,null);

        SharedInstance.main.makeRequest(url, null, body, (json, exception) -> {
            Response r = new Response(json);
            r.setException(exception);

            if(exception == null) {
                JsonElement data = json.get("data");
                List<T> list = gson.fromJson(data, new TypeToken<List<T>>(){}.getType());
                callback.execute(r,list);
            }else {
                callback.execute(r,null);
            }
        });
    }

    private void do_upsert(Upsert<T>  callback, T input){
        JsonObject body = new JsonObject();
        build(body,UPSERT,input);
        SharedInstance.main.makeRequest(url, null, body, (json, exception) -> {
            Response r = new Response(json);
            r.setException(exception);
            callback.execute(r);
        });

    }

    private void do_delete(Delete<T>  callback, T input){
        JsonObject body = new JsonObject();
        build(body,DELETE,input);

        SharedInstance.main.makeRequest(url, null, body, (json, exception) -> {
            Response r = new Response(json);
            r.setException(exception);
            callback.execute(r);
        });
    }

    private void build(JsonObject body, String action, T input){


        addAction(action,body);
        if(input != null) {
            JsonObject params = gson.toJsonTree(input).getAsJsonObject();
            setParameters(params, body);
        }

        affiliate(body);
    }

    private void addAction(String action,JsonObject object){
        object.addProperty("action",action);
    }

    private void affiliate(JsonObject object){
        object.addProperty("ACCOUNT_ID", AutoSignIn.ID);
        object.addProperty("SESSION_TOKEN",AutoSignIn.SESSION_TOKEN);
    }

    private void setParameters(JsonObject params, JsonObject body){
        body.add("parameters",params);
    }


    @FunctionalInterface
    public interface Read<T>{
        void execute(Response response, T object);
    }

    @FunctionalInterface
    public interface ReadAll<T> {
        void execute(Response response, List<T> items);
    }

    @FunctionalInterface
    public interface Upsert<T> {
        void execute(Response response);
    }

    @FunctionalInterface
    public interface Delete<T> {
        void execute(Response response);
    }
}