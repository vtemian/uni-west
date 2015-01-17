package orm.components;

import orm.connection.IConnection;
import orm.entity.IEntity;
import java.util.List;

public interface IORM {
    public void sync(List<IEntity> entitiesList);
    public void retrieve(Integer entityID, String entityClass);

    public void create(IEntity entity);
    public void update(IEntity entity);
    public void delete(IEntity entity);

    public void setConnection(IConnection dbConnection);
}
