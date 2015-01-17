package app;

import app.models.Line;
import orm.components.ORM;
import orm.connection.JDBCConnection;

public class Example {
    public static void main(String[] args){
        JDBCConnection connection = new JDBCConnection("seleus00", "root", "jdbc:mysql://localhost/test_orm");
        ORM orm = new ORM("app.models", connection);
        Line line = new Line();

        // sync db with models
        orm.sync();
    }
}
