package orm.connection;

import java.sql.*;

public class JDBCConnection implements IConnection{
    private String dbPass;
    private String dbUser;
    private String connectionString;
    private Statement stmt = null;

    public JDBCConnection(String dbPass, String dbUser, String connectionString) {
        this.dbPass = dbPass;
        this.dbUser = dbUser;
        this.connectionString = connectionString;
    }

    /**
     *  Open a connection to database and create a Statement used to run queries
     */
    public void connect(){
        try {
            // using Reflection API, dynamically load Mysql JDBC driver class
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // get a connection to connectionString using dbUser and dbPass as credentials
        try {
            Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Wrapper method used to execute an insert query in order to create a table
     * @param sqlStatement: String representing sql statement to execute
     */
    @Override
    public void createTable(String sqlStatement) {
        executeSQL(sqlStatement, "UPDATE");
    }

    /**
     * Wrapper method used to perform a SELECT query
     * @param sqlStatement: String representing the sql statement to execute
     * @return ResultSet
     */
    @Override
    public ResultSet select(String sqlStatement) {
        return executeSQL(sqlStatement, "SELECT");
    }

    /**
     *  Execute a given query of a given type (SELECT or UPDATE). It uses the Statement stmt initialized in connect
     *  method.
     * @param sqlStatement: String representing the sql statement to execute
     * @param type: String representing which type of statement to execute (SELECT, UPDAETE)
     * @return ResultSet: an object that contains the data fetched from db
     */
    @Override
    public ResultSet executeSQL(String sqlStatement, String type) {
        ResultSet rs = null;

        try{
            if(type.equals("SELECT")){
                // use executeQuery for SELECT statements
                rs = stmt.executeQuery(sqlStatement);
            } else if(type.equals("UPDATE")) {
                // otherwise we need to use executeUpdate
                stmt.executeUpdate(sqlStatement);
            }
            return rs;
        }catch(SQLException se){
            // handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            // handle errors for Class.forName
            e.printStackTrace();
        }//end try

        return null;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }

    public String getDbPass() {return dbPass; }

    public String getDbUser() {
        return dbUser;
    }

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}
