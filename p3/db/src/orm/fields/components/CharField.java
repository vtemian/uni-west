package orm.fields.components;

import orm.fields.interfaces.IField;

public class CharField implements IField{
    private String sqlString;
    private Integer maxSize;

    public CharField(Integer maxSize) {
        this.maxSize = maxSize;
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
