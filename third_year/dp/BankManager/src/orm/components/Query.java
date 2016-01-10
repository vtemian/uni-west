package orm.components;

import java.util.ArrayList;

public class Query implements IQuery{
    private ORM orm;
    private Class<?> entity;
    private String condition;

    public Query(ORM orm, Class<?> entity, String condition) {
        this.orm = orm;
        this.entity = entity;
        this.condition = condition;
    }

    @Override
    public IQuery where(String key, String value) {
        condition += " WHERE " + key + "='" + value + "'";
        return new Query(orm, entity, condition);
    }

    @Override
    public IQuery and(String key, String value) {
        condition += " AND " + key + "='" + value + "'";
        return new Query(orm, entity, condition);
    }

    @Override
    public IQuery or(String key, String value) {
        condition += " OR " + key + "='" + value + "'";
        return new Query(orm, entity, condition);
    }

    @Override
    public ArrayList<Object> all() {
        return null;
    }

    @Override
    public Object first() {
        return orm.buildEntity(entity, orm.select(condition + ";"));
    }
}
