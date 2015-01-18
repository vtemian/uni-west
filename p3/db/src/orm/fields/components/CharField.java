package orm.fields.components;

import orm.fields.interfaces.IField;

public class CharField implements IField{
    private String sqlString;
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

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
    public Integer getMaxSize() {
        return maxSize;
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
