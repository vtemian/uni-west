package orm.entity;

import orm.entity.IEntity;
import orm.fields.interfaces.IField;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements IEntity {
    private String name;
    private ArrayList<IField> fields = new ArrayList<IField>();

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addField(IField field) {
        fields.add(field);
    }

    @Override
    public List<IField> getFields() {
        return fields;
    }
}
