package orm.components;

import orm.connection.IConnection;
import orm.entity.IEntity;
import orm.fields.interfaces.IField;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ORM implements IORM{
    private List<IEntity> entitiesList;
    private IConnection dbConnection;

    public ORM(IConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void sync(List<IEntity> entitiesList) {
        String createTable = "";

        for(IEntity entity: entitiesList){
            createTable += "CREATE TABLE IF NOT EXISTS (";

            int counter = 0;
            for(String statement: getFieldsSQLStatemens(entity)){
                if(counter > 0)
                    createTable += ",";
                createTable += statement;
                counter++;
            }
            createTable += ");";
        }

        System.out.println(createTable);
        //dbConnection.createTable(createTable);
    }

    public List<String> getFieldsSQLStatemens(IEntity entity){
        Class<?> clazz = entity.getClass();
        List<String> sqlStatements = new ArrayList<String>();

        for(Field field : clazz.getDeclaredFields()) {
            try {
                Class x = Class.forName(field.getType().getName());

                for(Class inter: x.getInterfaces()){
                    if(inter.getName().equals("orm.fields.interfaces.IField")){
                        Method method = x.getDeclaredMethod("getSQLStatement");
                        String sqlStatement = (String) method.invoke(field.get(entity));
                        sqlStatements.add(field.getName() + " " + sqlStatement);
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sqlStatements;
    }

    @Override
    public void retrieve(Integer entityID, String entityClass) {

    }

    @Override
    public void create(IEntity entity) {

    }

    @Override
    public void update(IEntity entity) {

    }

    @Override
    public void delete(IEntity entity) {

    }

    @Override
    public void setConnection(IConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
