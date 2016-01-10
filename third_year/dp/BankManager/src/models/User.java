package models;

import orm.entity.Entity;
import orm.fields.components.AutoIncrementField;
import orm.fields.components.BooleanField;
import orm.fields.components.CharField;

public class User extends Entity{
    public AutoIncrementField ID = new AutoIncrementField();
    public CharField username = new CharField("", 250);
    public CharField password = new CharField("", 250);
    public CharField fullName = new CharField("", 250);
    public BooleanField userIsAdmin = new BooleanField(false);

    public User(){}

    public User(String[] row){
        username.setValue(row[0]);
        password.setValue(row[1]);
        fullName.setValue(row[2]);
        userIsAdmin.setValue(row[4]);
    }

    public User(String username, String password, String fullName, boolean isAdmin) {
        this.username.setValue(username);
        this.password.setValue(password);
        this.fullName.setValue(fullName);
        this.userIsAdmin.setValue(isAdmin);
    }

    public Boolean isAdmin(){
        return userIsAdmin.getValue().equals("1");
    }
}
