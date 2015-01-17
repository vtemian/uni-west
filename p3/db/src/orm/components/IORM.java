package orm.components;

import orm.connection.IConnection;
import orm.entity.IEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IORM {
    public void sync();
    public void retrieve(Integer entityID, String entityClass);

    public void create(IEntity entity);
    public void update(IEntity entity);
    public void delete(IEntity entity);

    public void setConnection(IConnection dbConnection);
    public Map<String, ArrayList<String>> getFieldsSQLStatements();
}
