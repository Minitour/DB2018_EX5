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

    final static int USERNAME = config.get("user").getAsJsonObject().get("max_allowed_sessions").getAsInt();
    final static long PASSWORD = config.get("user").getAsJsonObject().get("session_time_out").getAsInt();
    final static String DB_LOCATION = config.get("db").getAsJsonObject().get("access_file_location").getAsString();

    private Connection connection;

    @Override
    public void close() throws Exception {
        if(!connection.isClosed())
            connection.close();
    }

    Database() throws SQLException {
        try { 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                 connection = DriverManager.getConnection(
                        "jdbc:sqlserver://minitour.mooo.com;databaseName=country_db",
                    "sa",
                    "********");  

            if(connection!=null) 
            System.out.println("Database Successfully connected"); 
             } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) { 
            e.printStackTrace(); 
        }
    }
}
