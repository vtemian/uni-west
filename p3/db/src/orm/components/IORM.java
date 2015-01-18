package orm.components;

import app.models.Line;
import orm.connection.IConnection;
import orm.entity.IEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IORM {
    public void sync();
    public Object retrieve(Integer entityID, Class<?> entity) throws IllegalAccessException, InstantiationException;

    public void create(IEntity entity);
    public void update(IEntity entity);
    public void delete(IEntity entity);

    public void setConnection(IConnection dbConnection);
    public Map<String, ArrayList<String>> getFieldsSQLStatements();
}
