package orm.fields.components;

import orm.fields.interfaces.IField;

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

    public void setValue(Object o){
        value = Integer.parseInt(o.toString());
    }
}
