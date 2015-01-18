package orm.fields.components;

import orm.fields.interfaces.IField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoIncrementField implements IField{
    private String sqlString;
    private Integer value = 0;

    @Override
    public String getSQLStatement() {
        return  " int NOT NULL AUTO_INCREMENT, PRIMARY KEY(";
    }

    public String getValue(){
        return value.toString();
    }

    public void setValue(ResultSet resultSet, String fieldName){
        try {
            value = resultSet.getInt(fieldName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setValue(Object o){
        value = Integer.parseInt(o.toString());
    }
}
