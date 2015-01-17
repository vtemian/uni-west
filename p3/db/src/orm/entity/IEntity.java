package orm.entity;
import orm.fields.interfaces.IField;

import java.util.List;

public interface IEntity {
    public String getName();
    public void setName(String name);
    public List<IField> getFields();
    public void addField(IField field);
}
