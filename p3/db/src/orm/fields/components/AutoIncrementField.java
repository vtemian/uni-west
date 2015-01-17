package orm.fields.components;

import orm.fields.interfaces.IField;

public class AutoIncrementField implements IField{
    private String sqlString;
    @Override
    public String getSQLStatement() {
        return  " int NOT NULL AUTO_INCREMENT ";
    }
}
