package database;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By Tony on 19/07/2018
 */
public class GenericAccess<E extends DBObject> extends DataAccess<E> {

    public static final String GET_ALL = "_READ_ALL";
    public static final String GET_BY_ID = "_READ";
    public static final String UPSERT = "_UPSERT";
    public static final String DELETE = "_DELETE";

    final private Class<E> classType;
    final private String tableName;


    public GenericAccess(Class<E> classType){
        super();
        this.classType = classType;
        this.tableName = null;
    }

    public GenericAccess(Class<E> classType,String tableName){
        super();
        this.classType = classType;
        this.tableName = tableName;
    }

    public void upsert(Object... params) throws SQLException{
        super.get(classType,proc(UPSERT),params);
    }

    public List<E> getById(Object id) throws SQLException {
       return super.get(classType,proc(GET_BY_ID),id);
    }

    public List<E> getAll() throws  SQLException {
        return super.get(classType,proc(GET_ALL));
    }

    public void delete(Object... params) throws SQLException{
        super.get(classType,proc(DELETE),params);
    }

    public String name(){
       return tableName == null ? classType.getSimpleName().trim().toLowerCase() : tableName;
    }

    public String proc(String s){
        return name() + s;
    }

}
