package app;

import app.entities.Line;
import orm.components.ORM;
import orm.connection.JDBCConnection;
import orm.entity.IEntity;

import java.util.ArrayList;

public class Example {
    public static void main(String[] args){
        JDBCConnection connection = new JDBCConnection("seleus00", "root", "jdbc:mysql://localhost/test_orm");
        ArrayList<IEntity> entities = new ArrayList<IEntity>();
        ORM orm = new ORM(connection);

        entities.add(new Line());
        orm.sync(entities);
    }
}
