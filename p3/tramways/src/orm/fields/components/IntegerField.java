package orm.fields.components;

import orm.entity.IEntity;
import orm.fields.interfaces.IField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerField implements IField{
    private Integer value;

    public IntegerField(Integer defaultValue) {
        this.value = defaultValue;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getValue(){
        return "'" + value + "'";
    }

    /**
     * Set a specific value from an ResultSet object, as a result of a select query
     * @param resultSet: a row from a given query
     * @param fieldName: specific column name from a table
     */
    public void setValue(ResultSet resultSet, String fieldName) {
        try {
            value = resultSet.getInt(fieldName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSQLStatement() {
        return  " int ";
    }

    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }
    @Override
    public void setValue(Object o) {
        value = (Integer) o;
    }
}
