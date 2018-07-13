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

    public Database() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection(DB_LOCATION + ";databaseName="+DATABASE_NAME,USERNAME,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Use this method to delete entries.
     *
     * Usage Example:
     *      @code {
     *
     *          //Example for deleting a session via token or id
     *          delete("SESSIONS","ID = ? OR TOKEN = ?",id,token);
     *
     *          //Simple Example for Deleting an account with a certain email.
     *          delete("ACCOUNTS","EMAIL = ?",email);
     *
     *          //Deleting multiple accounts with ids 32,542,22
     *          delete("ACCOUNTS","ID in (?, ?, ?)",32,542,22);
     *      }
     *
     * @param table The name of the table.
     * @param where The predicate/condition.
     * @param values The values.
     * @throws SQLException
     */
    public boolean delete(String table, String where, Object... values) throws SQLException{

        String query = "DELETE FROM "+ table +" WHERE "+ where;

        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        int index = 1;
        for(Object obj : values)
            statement.setObject(index++,obj);

        return statement.executeUpdate() != 0;
    }

    /**
     * Use this method to delete multiple entries. Instead of inserting many wildcards use `#` operator.
     *
     * @code {
     *
     *          deleteMany("ACCOUNTS","ID in (#)",32,542,22)
     *
     *          //Is the same as:
     *          delete("ACCOUNTS","ID in (?, ?, ?)",32,542,22);
     *      }
     *
     * @param table
     * @param where
     * @param values
     * @return
     * @throws SQLException
     */
    public boolean deleteMany(String table,String where,String... values) throws SQLException{
        StringBuilder builder = new StringBuilder();

        for(String ignored : values)
            builder.append("?").append(",");

        builder.deleteCharAt(builder.length()-1);

        return delete(table,where.replace("#",builder.toString()),values);
    }

    /**
     *
     * Use this method to insert data into a certain table.
     *
     * Usage Example:
     *      @code {
     *          insert("SESSIONS",
     *               new Column("ID",id),
     *               new Column("SESSION_TOKEN",token),
     *               new Column("CREATION_DATE",new Date())
     *          );
     *      }
     *
     *      @see Column
     *
     *
     * @param table The name of the table.
     * @param values Var args of Pairs of Type (String:Object). Use Column for ease of use.
     * @param <T> The type of the generated key.
     * @return
     * @throws SQLException
     */
    public<T> T insert(String table, Column...values) throws SQLException {

        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();

        for(Column value : values){
            if(!value.shouldIgnore()) {
                builder1.append(value.getKey()).append(",");
                builder2.append("?").append(",");
            }
        }

        builder1.deleteCharAt(builder1.length() - 1);
        builder2.deleteCharAt(builder2.length() - 1);

        String query  ="INSERT INTO "+table+" ("+builder1.toString()+") VALUES ("+builder2.toString()+");";

        PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

        int index = 1;
        for(Column obj : values)
            if(!obj.shouldIgnore())
                statement.setObject(index++,obj.getValue());

        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();

        return rs.next() ? (T) rs.getObject(1) : null;
    }

    /**
     *
     * @param table
     * @param dbObject
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> T insert(String table,DBObject dbObject) throws SQLException {
        return insert(table,dbObject.db_columns());
    }

    /**
     *
     * @param object
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> T insert(DBObject object) throws SQLException{
        return insert(object.db_table(),object.db_columns());
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
    public List<Map<String,Object>> get(String query, Object...args) throws SQLException {
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

    /**
     * Use this method to update an existing entry.
     *
     * @param table The table
     * @param where The condition
     * @param values The values to set/update
     * @return
     * @throws SQLException
     */
    public boolean update(String table,Where where, Column...values) throws SQLException {

        StringBuilder builder = new StringBuilder();

        for(Column value : values)
            builder.append(value.getKey()).append(" = ").append("?,");

        builder.deleteCharAt(builder.length()-1);

        String query  ="UPDATE "+table+" SET "+builder.toString()+" WHERE "+where.syntax;
        PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

        int index = 1;
        for(Column obj : values)
            if(!obj.shouldIgnore())
                statement.setObject(index++,obj.getValue());

        for(Object o : where.values)
            statement.setObject(index++,o);

        return statement.executeUpdate() != 0;
    }

    class Where {
        final String syntax;
        final Object[] values;

        public Where(String syntax, Object...values) {
            this.syntax = syntax;
            this.values = values;
        }
    }
}
