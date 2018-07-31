package utils;

import java.lang.reflect.Field;

/**
 * Created By Tony on 31/07/2018
 */
public interface Searchable {
    default String serachKey(){
        StringBuilder builder = new StringBuilder();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String index = String.valueOf(field.get(this));
                builder.append(index).append(" ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
