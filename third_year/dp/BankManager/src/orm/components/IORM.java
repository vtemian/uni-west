package orm.components;

import orm.connection.IConnection;
import orm.entity.IEntity;

import java.util.ArrayList;
import java.util.Map;

public interface IORM {
    void sync();

    IQuery retrieve(Class<?> entity);

    Object get(Integer entityID, Class<?> entity) throws IllegalAccessException, InstantiationException;
    void create(IEntity entity);
    void update(IEntity entity);
    void delete(IEntity entity);

    void setConnection(IConnection dbConnection);
    Map<String, ArrayList<String>> getFieldsSQLStatements();
}
