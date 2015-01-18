package orm.connection;

import java.sql.ResultSet;

public interface IConnection {
    public String getConnectionString();
    public void setConnectionString(String connectionString);

    public void createTable(String sqlStatement);
    public ResultSet select(String sqlStatement);
    public ResultSet executeSQL(String sqlStatement, String type);
}
