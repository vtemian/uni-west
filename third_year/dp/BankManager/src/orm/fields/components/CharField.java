package orm.fields.components;

import orm.fields.interfaces.IField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CharField implements IField{
    private String value;
    private Integer maxSize;

    public CharField(String defaultValue, Integer maxSize) {
        this.maxSize = maxSize;
        this.value = defaultValue;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue(){
        return "'" + value + "'";
    }

    public String getRawValue(){
        return value;
    }


    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
    public Integer getMaxSize() {
        return maxSize;
    }

    /**
     * Set a specific value from an ResultSet object, as a result of a select query
     * @param resultSet: a row from a given query
     * @param fieldName: specific column name from a table
     */
    public void setValue(ResultSet resultSet, String fieldName) {
        try {
            value = resultSet.getString(fieldName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setValue(Object o) {
        value = o.toString();
    }

    @Override
    public String getSQLStatement() {
        return  " varchar(" + maxSize.toString() + ") ";
    }
}
