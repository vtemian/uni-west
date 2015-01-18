package orm.fields.interfaces;

import java.sql.ResultSet;

public interface IField {
    public String getSQLStatement();
    public void setValue(Object o);
    public void setValue(ResultSet resultSet, String fieldName);
    public String getValue();
}
