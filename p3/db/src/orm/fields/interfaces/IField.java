package orm.fields.interfaces;

public interface IField {
    public String getSQLStatement();
    public void setValue(Object o);
}
