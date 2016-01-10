package orm.components;

import java.util.ArrayList;

public interface IQuery {
    IQuery where(String key, String value);
    IQuery and(String key, String value);
    IQuery or(String key, String value);

    ArrayList<Object> all();
    Object first();
}
