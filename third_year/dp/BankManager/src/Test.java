import gui.login.LoginFrame;
import models.EURAccount;
import models.User;
import models.Bank;
import orm.components.ORM;
import orm.connection.JDBCConnection;

public class Test {

	public static void main(String[] args) {
        String dbConnectionString = "jdbc:mysql://localhost/bankmanager";
        JDBCConnection connection = new JDBCConnection("seleus00", "root", dbConnectionString);
        //JDBCConnection connection = new JDBCConnection("b@nkm@n@g3r", "bankmanager", dbConnectionString);

        ORM orm = new ORM("models", connection);
        orm.sync();

        User admin = new User("admin", "admin", "Vlad Temian", true);
        orm.create(admin);

        Bank bank = new Bank("BCR");
        orm.create(bank);

        LoginFrame login = new LoginFrame();
	}
}
