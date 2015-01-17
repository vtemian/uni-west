package orm.components;

import orm.connection.IConnection;
import orm.entity.IEntity;
import orm.fields.interfaces.IField;

import java.util.List;

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
            createTable += "CREATE TABLE IF NOT EXISTS ";
            createTable += entity.getName() + "( ";

            int counter = 0;
            for(IField field: entity.getFields()) {
                if(counter > 0)
                    createTable += ",";
                createTable += field.getSQLStatement();
                counter += 1;
            }

            createTable += ");";
        }

        System.out.println(createTable);
        dbConnection.createTable(createTable);
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
