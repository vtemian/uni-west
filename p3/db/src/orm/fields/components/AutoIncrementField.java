package orm.fields.components;

import orm.fields.interfaces.IField;

public class AutoIncrementField implements IField{
    private String sqlString;
    private String name = "ID";

    @Override
    public String getName() {
        return "ID";
    }

    @Override
    public String getSQLStatement() {
        return name + " int NOT NULL AUTO_INCREMENT ";
    }
}
