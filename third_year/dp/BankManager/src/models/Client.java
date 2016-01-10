package models;

import orm.entity.Entity;
import orm.fields.components.AutoIncrementField;
import orm.fields.components.CharField;
import orm.fields.components.IntegerField;

public class Client extends Entity{
    public AutoIncrementField ID = new AutoIncrementField();
    public IntegerField userID = new IntegerField(0);
    public CharField fullName = new CharField("", 250);
    public CharField address = new CharField("", 250);

    public Client(){}

    public Client(String[] row){
        userID.setValue(row[0]);
        fullName.setValue(row[1]);
        address.setValue(row[3]);
    }

    public Client(Integer userID, String fullName, String address){
        System.out.println(userID.toString());
        this.userID.setValue(userID);
        this.fullName.setValue(fullName);
        this.address.setValue(address);
    }

}
