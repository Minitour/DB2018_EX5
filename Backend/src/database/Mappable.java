package database;

import java.util.Map;

public interface Mappable {

    default void map(Map map){
        System.err.println("Not Implemented");
    }

    default Map getMap(){ return null; }

    default Column[] db_columns(){
        return new Column[]{};
    }

}
