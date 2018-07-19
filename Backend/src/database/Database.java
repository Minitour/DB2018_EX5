package database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Config.config;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public abstract class Database implements AutoCloseable {

    final static String USERNAME = config.get("db").getAsJsonObject().get("username").getAsString();
    final static String PASSWORD = config.get("db").getAsJsonObject().get("password").getAsString();
    final static String DB_LOCATION = config.get("db").getAsJsonObject().get("location").getAsString();
    final static String DATABASE_NAME = config.get("db").getAsJsonObject().get("database").getAsString();

    private Connection connection;

    @Override
    public void close() throws Exception {
        if(!connection.isClosed())
            connection.close();
    }

    public Database() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection(DB_LOCATION + ";databaseName="+DATABASE_NAME,USERNAME,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Use this method to make Database Queries.
     *
     * Usage Example:
     *      @code { get("SELECT * FROM USERS WHERE EMAIL = ?",email) }
     *
     * @param query The Query String.
     * @return A List Of Hash Maps of Type (String:Object)
     * @throws SQLException
     */
    protected List<Map<String,Object>> get(String query, Object[]args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);

        int index = 1;
        for(Object val : args)
            statement.setObject(index++,val);

        ResultSet set = statement.executeQuery();

        String[] columns = new String[set.getMetaData().getColumnCount()];

        for (int i = 1; i <= columns.length; i++ )
            columns[i - 1] = set.getMetaData().getColumnName(i);

        List<Map<String,Object>> data = new ArrayList<>();

        while (set.next()){
            Map<String,Object> map = new HashMap<>();
            for(String name : columns)
                map.put(name,set.getObject(name));

            data.add(map);

        }

        return data;
    }

}
