package orm.connection;

import java.sql.*;

public class JDBCConnection implements IConnection{
    private String dbPass;
    private String dbUser;
    private String connectionString;

    static final String jdbcDriver = "com.mysql.jdbc.Driver";

    public JDBCConnection(String dbPass, String dbUser, String connectionString) {
        this.dbPass = dbPass;
        this.dbUser = dbUser;
        this.connectionString = connectionString;
    }

    @Override
    public void createTable(String sqlStatement) {
        executeSQL(sqlStatement, "UPDATE");
    }

    @Override
    public ResultSet executeSQL(String sqlStatement, String type) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;

        try{
            //Register JDBC driver
            Class.forName(jdbcDriver);

            //Open a connection
            conn = DriverManager.getConnection(connectionString, dbUser, dbPass);

            //Create a statement
            stmt = conn.createStatement();
            if(type.equals("SELECT"))
                result = stmt.executeQuery(sqlStatement);
            if(type.equals("UPDATE"))
                stmt.executeUpdate(sqlStatement);

            //Cleanup
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return result;
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
