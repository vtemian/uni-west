package models;

import orm.entity.Entity;
import orm.fields.components.AutoIncrementField;
import orm.fields.components.BooleanField;
import orm.fields.components.CharField;

public class User extends Entity{
    public AutoIncrementField ID = new AutoIncrementField();
    public CharField username = new CharField("", 250);
    public CharField password = new CharField("", 250);
    public CharField email = new CharField("", 250);
    public BooleanField userIsAdmin = new BooleanField(false);

    public User(){}

    public User(String[] row){
        username.setValue(row[0]);
        password.setValue(row[1]);
        email.setValue(row[2]);
        userIsAdmin.setValue(row[3]);
    }

    public User(String username, String password, String email, boolean isAdmin) {
        this.username.setValue(username);
        this.password.setValue(password);
        this.email.setValue(email);
        this.userIsAdmin.setValue(isAdmin);
    }

    public Boolean isAdmin(){
        return userIsAdmin.getValue().equals("1");
    }

    public static User generateFromEmail(String email) {
        String username = email.split("@")[0];
        String password = email.split("@")[0];
        return new User(username, password, email, false);
    }
}