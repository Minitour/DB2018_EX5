package database.access;

import database.DBObject;
import database.Database;

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


    public GenericAccess(Class<E> classType, String tableName, Database existingDatabase) {
        super(existingDatabase);
        this.classType = classType;
        this.tableName = tableName;
    }

    public GenericAccess(Class<E> classType){
        this(classType,null);
    }

    public GenericAccess(Class<E> classType,String tableName){
        super();
        this.classType = classType;
        this.tableName = tableName;
    }


    public Integer upsert(DBObject object) throws AccessException {
        return this.upsert(object.flatValues());
    }

    public Integer upsert(Object... params) throws AccessException{
        List<E> data = super.get(classType, proc(UPSERT), params);

        if (data.size() == 1)
            return data.get(0).getInsertedID();

        return 0;
    }

    public List<E> getById(Object... params) throws AccessException {
        return super.get(classType, proc(GET_BY_ID), params);

    }

    public List<E> getAll() throws  AccessException {
        return super.get(classType, proc(GET_ALL));
    }

    public void delete(Object... params) throws AccessException{
        super.get(classType, proc(DELETE), params);
    }

    protected String name(){
       return tableName == null ? classType.getSimpleName().trim().toLowerCase() : tableName;
    }

    protected String proc(String s){
        return name() + s;
    }

}
