package app.entities;


import orm.fields.components.AutoIncrementField;
import orm.fields.components.CharField;
import orm.entity.Entity;

public class Line extends Entity{
    public AutoIncrementField ID = new AutoIncrementField();
    public CharField name = new CharField("name", new Integer(200));
}
