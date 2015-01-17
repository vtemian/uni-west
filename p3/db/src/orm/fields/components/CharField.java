package orm.fields.components;

import orm.fields.interfaces.IField;

public class CharField implements IField{
    private String sqlString;
    private String name;
    private Integer maxSize;

    @Override
    public String getName() {
        return null;
    }

    public CharField(String name, Integer maxSize) {
        this.maxSize = maxSize;
        this.name = name;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    @Override
    public String getSQLStatement() {
        return name + " varchar(" + maxSize.toString() + ") ";
    }
}
