package database;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created By Tony on 19/07/2018
 */
public class DataAccess<E extends DBObject> extends Database {

    public List<E> get(Class<E> cls,String procedure, Object... args) throws SQLException {
        List<Map<String, Object>> data = procedure_any(procedure, args);
        List<E> result = new ArrayList<>();
        for (Map<String, Object> map : data) {
            E value = initFrom(map, cls);
            result.add(value);
        }
        return result;
    }

    public List<Map<String,Object>> procedure_any(String procedure,Object[] args) throws SQLException {
        return get(call(procedure,args.length),args);
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

    public <E extends DBObject> E initFrom(Map<String,Object> data, Class<E> type){
        try {
            E e = type.getConstructor().newInstance();
            e.map(data);
            return e;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
