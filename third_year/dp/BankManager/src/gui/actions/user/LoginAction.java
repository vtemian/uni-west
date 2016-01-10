package gui.actions.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.login.LoginFrame;
import gui.main.BankForm;
import models.Client;
import models.User;
import orm.components.ORM;

public class LoginAction implements ActionListener {

	private LoginFrame frame;

	public LoginAction(LoginFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getlErrors().setText(" ");

		String username = frame.getTfUsername().getText().trim();
		String password = new String(frame.getTfPassword().getPassword());

        User user = (User) ORM.getInstance().retrieve(User.class).where("username", username).
                                                                  and("password", password).first();
        if (user == null) {
            frame.getlErrors().setText("<html><font color=#ff0000>Invalid username or password");
        } else {
            if (user.isAdmin()){
                frame.setVisible(false);
                new BankForm((String)frame.getCbBank().getSelectedItem());
            }else{
                //TODO: get client's interface
            }
        }
	}
}
