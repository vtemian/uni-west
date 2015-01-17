package app.entities;


import orm.fields.components.CharField;
import orm.entity.Entity;

public class Line extends Entity{
    public Line() {
        super.setName("Line");

        addField(new CharField("name", new Integer(200)));
        addField(new CharField("type", new Integer(200)));
    }
}
