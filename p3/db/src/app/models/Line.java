package app.models;


import orm.entity.Entity;
import orm.fields.components.CharField;
import orm.fields.components.AutoIncrementField;

public class Line extends Entity{
    public AutoIncrementField ID = new AutoIncrementField();
    public CharField name = new CharField("", new Integer(200));
}
