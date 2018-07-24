package database.access;

import database.DBObject;
import database.Database;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created By Tony on 19/07/2018
 */
public class DataAccess<E extends DBObject> extends Database {

    public DataAccess() { }

    public DataAccess(Database existingDatabase) {
        super(existingDatabase);
    }


    protected<E extends DBObject> List<E> get(Class<E> cls, String procedure, Object... args) throws AccessException {
        List<Map<String, Object>> data = procedure_any(procedure, args);

        if(data == null)
            return null;

        List<E> result = new ArrayList<>();
        for (Map<String, Object> map : data) {
            E value = initFrom(map, cls);
            result.add(value);
        }
        return result;
    }

    private List<Map<String,Object>> procedure_any(String procedure, Object[] args) throws AccessException {
        try {
            return get(call(procedure, args.length), args);
        }catch (SQLException e){
            int code = e.getErrorCode();

            String message;
            switch (code){
                case 2812:
                    message = "Procedure Not Found."; break;

                default:
                    message = e.getMessage(); break;
            }

            throw new AccessException(message);
        }
    }

    private static String call(String proc,int args){
        return "{ call "+proc +" " +args(args) + " }";
    }

    private static String args(int count) {
        if(count == 0)
            return "";

        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (int i = 0; i < count; i++) {
            builder.append("?,");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.append(")").toString();
    }

    protected <E extends DBObject> E initFrom(Map<String,Object> data, Class<E> type){
        try {
            Constructor<E> constructor = (Constructor<E>) type.getDeclaredConstructors()[0];
            int count = constructor.getParameterCount();
            Object[] nullArgs = new Object[count];
            E e = constructor.newInstance(nullArgs);
            e.map(data);
            return e;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
