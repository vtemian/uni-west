package orm.fields.components;

import orm.fields.interfaces.IField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanField implements IField{
    private Boolean value;

    public BooleanField(Boolean defaultValue) {
        this.value = defaultValue;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getValue(){
        if (value)
            return "1";
        return "0";
    }

    /**
     * Set a specific value from an ResultSet object, as a result of a select query
     * @param resultSet: a row from a given query
     * @param fieldName: specific column name from a table
     */
    public void setValue(ResultSet resultSet, String fieldName) {
        try {
            value = resultSet.getBoolean(fieldName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSQLStatement() {
        return  " bool ";
    }

    public void setValue(String value) {
        this.value = Boolean.parseBoolean(value);
    }
    @Override
    public void setValue(Object o) {
        value = (Boolean) o;
    }
}
