package app;

import app.models.Line;
import orm.components.ORM;
import orm.connection.JDBCConnection;

public class Example {
    public static void main(String[] args){
        JDBCConnection connection = new JDBCConnection("seleus00", "root", "jdbc:mysql://localhost/test_orm");
        ORM orm = new ORM("app.models", connection);
        Line line = new Line();

        line.name.setValue("altcineva");

        // sync db with models
        try {
            line = (Line) orm.retrieve(10, Line.class);
            System.out.println(line.name.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
