package database;

import com.google.gson.annotations.Expose;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created By Tony on 12/02/2018
 */
public abstract class DBObject{

    /**
     * A hash map which holds a list of foreign keys. This map will be populated with values only when calling the
     * @code {DBObject(Map<String,Object> map)} Constructor.
     */
    final transient private Map<String,Object> foreignKeys = new HashMap<>();
    final transient private Map<String,Object> computedFields = new HashMap<>();

    /**
     * Default Constructor
     */
    public DBObject(){ }

    /**
     * Constructor from map object.
     *
     * @param map The map which contains the values.
     */
    public DBObject(Map<String,Object> map){
        map(map);
    }

    public void map(Map<String,Object> map){
        Set<Field> fields = findFields(Expose.class);
        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                field.set(this,map.get(fieldName));
                map.remove(field.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }catch (IllegalArgumentException e){
                //field is a foreign key!
                foreignKeys.put(field.getName(),map.get(field.getName()));
            }finally {
                map.forEach(computedFields::put);
            }
        });
    }

    /**
     * Helper method which returns a list of fields that are annotated with a certain annotation.
     * @param ann
     * @return
     */
    protected Set<Field> findFields(Class<? extends Annotation> ann) {
        Set<Field> set = new HashSet<>();
        Class<?> c = getClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    set.add(field);
                }
            }
            c = c.getSuperclass();
        }
        return set;
    }

    protected List<Field> findFieldsAsList(Class<? extends Annotation> ann) {
        List<Field> list = new ArrayList<>();

        Class<?> c = getClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    list.add(field);
                }
            }
            c = c.getSuperclass();
        }
        return list;
    }

    /**
     * A method which returns a foreign key's value.
     * @param key The foreign key
     * @param <T> The type
     * @return The value if found else null.
     */
    public <T> T getForeignKey(String key){
        return (T) foreignKeys.get(key);
    }

    /**
     * Get a map of all foreign keys and their values.
     * @return
     */
    public Map<String,Object> getForeignKeys(){
        return Collections.unmodifiableMap(foreignKeys);
    }

    /**
     * Check if there are any foreign keys.
     * @return true if there are any foreign keys.
     */
    public boolean hasForeignKeys(){
        return getForeignKeys().size() > 0;
    }

    public String db_table(){
        return "Tbl" + getClass().getSimpleName() + "s";
    }

    public Column[] db_columns(){
        return new Column[]{};
    }

    public Object[] flatValues(){
        List<Field> fields = findFieldsAsList(Expose.class);
        Object[] o = new Object[fields.size()];

        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            f.setAccessible(true);
            try {
                o[i] = fields.get(i).get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return o;
    }

    public int getInsertedID(){
        Object val = computedFields.get("InsertedID");
        try {
            return (int) val;
        }catch (Exception e) {  return -1; }
    }

    @Override
    public String toString() {
        return Arrays.toString(flatValues());
    }
}
