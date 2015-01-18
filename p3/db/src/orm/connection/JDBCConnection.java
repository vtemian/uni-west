package orm.connection;

import java.sql.*;
import java.util.HashMap;

public class JDBCConnection implements IConnection{
    private String dbPass;
    private String dbUser;
    private String connectionString;
    private Connection conn = null;
    private Statement stmt = null;

    static final String jdbcDriver = "com.mysql.jdbc.Driver";

    public JDBCConnection(String dbPass, String dbUser, String connectionString) {
        this.dbPass = dbPass;
        this.dbUser = dbUser;
        this.connectionString = connectionString;

        try {
            //Register JDBC driver
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(String sqlStatement) {
        executeSQL(sqlStatement, "UPDATE");
    }

    @Override
    public ResultSet select(String sqlStatement) {
        return executeSQL(sqlStatement, "SELECT");
    }

    @Override
    public ResultSet executeSQL(String sqlStatement, String type) {
        ResultSet rs = null;

        try{
            if(type.equals("SELECT"))
                rs = stmt.executeQuery(sqlStatement);
            if(type.equals("UPDATE"))
                stmt.executeUpdate(sqlStatement);
            return rs;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
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

    public String getDbPass() {
        return dbPass;
    }

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
