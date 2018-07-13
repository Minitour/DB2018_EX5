package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.Config.config;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class Database implements AutoCloseable {

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

    public Database() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection(
                    DB_LOCATION + ";databaseName="+DATABASE_NAME,
                    USERNAME,
                    PASSWORD);  
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
