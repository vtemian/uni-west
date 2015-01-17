package app;

import app.models.Line;
import orm.components.ORM;
import orm.connection.JDBCConnection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Example {
    public static void main(String[] args){
        JDBCConnection connection = new JDBCConnection("seleus00", "root", "jdbc:mysql://localhost/test_orm");
        ORM orm = new ORM("app.models", connection);
        orm.sync();
    }
}
