package app.models;


import orm.fields.components.AutoIncrementField;
import orm.fields.components.CharField;
import orm.entity.Entity;

public class Line extends Entity{
    public static final AutoIncrementField ID = new AutoIncrementField();
    public static final CharField name = new CharField(new Integer(200));
}
