package app.models;


import orm.entity.Entity;
import orm.fields.components.AutoIncrementField;
import orm.fields.components.CharField;
import orm.fields.interfaces.IField;

public class Line extends Entity{
    public final CharField name = new CharField("", new Integer(200));
    public final AutoIncrementField ID = new AutoIncrementField();
}
