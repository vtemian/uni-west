package orm.fields.components;

import orm.fields.interfaces.IField;

public class CharField implements IField{
    private String sqlString;
    private String defaultValue;
    private Integer maxSize;

    public CharField(String defaultValue, Integer maxSize) {
        this.maxSize = maxSize;
        this.defaultValue = defaultValue;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
    public Integer getMaxSize() {
        return maxSize;
    }

    @Override
    public String getSQLStatement() {
        return  " varchar(" + maxSize.toString() + ") ";
    }
}
