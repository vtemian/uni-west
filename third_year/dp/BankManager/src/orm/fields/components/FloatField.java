package orm.fields.components;

import orm.fields.interfaces.IField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FloatField implements IField{
    private Float value;

    public FloatField(Float defaultValue) {
        this.value = defaultValue;
    }

    public void setValue(Float value) {
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
            value = resultSet.getFloat(fieldName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSQLStatement() {
        return  " float(13,10) ";
    }

    @Override
    public void setValue(Object o) {
        value = (Float) o;
    }

    public void setValue(String value){
        this.value = Float.parseFloat(value);
    }
}
