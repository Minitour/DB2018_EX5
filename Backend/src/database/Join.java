package database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created By Tony on 24/07/2018
 */
public class Join<A extends DBObject,B extends DBObject> extends DBObject {

    private A object_a;
    private B object_b;

    public Join(Class<A> clsA, Class<B> clsB){
        object_a = initFrom(clsA);
        object_b = initFrom(clsB);
    }

    @Override
    public void map(Map<String, Object> map) {

        super.map(map);

        if(object_a != null)
            object_a.map(map);

        if(object_b != null)
            object_b.map(map);
    }

    @Override
    protected List<Field> findFieldsAsList(Class<? extends Annotation> ann) {

        List<Field> alist = object_a.findFieldsAsList(ann);
        List<Field> blist = object_b.findFieldsAsList(ann);
        List<Field> current = super.findFieldsAsList(ann);

        alist.addAll(blist);
        alist.addAll(current);

        return alist;
    }

    private <E extends DBObject> E initFrom(Class<E> type){
        if(type == null)
            return null;
        try {
            Constructor<E> constructor = (Constructor<E>) type.getDeclaredConstructors()[0];
            int count = constructor.getParameterCount();
            Object[] nullArgs = new Object[count];
            E e = constructor.newInstance(nullArgs);
            return e;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public A left() {
        return object_a;
    }

    public B right() {
        return object_b;
    }

    @Override
    public String toString() {
        return object_a.toString() + object_b.toString();
    }
}
