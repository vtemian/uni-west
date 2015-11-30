package ro.uvt.gui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ro.uvt.gui.main.BankForm;

public class LoginAction implements ActionListener {

	private LoginFrame frame;

	public LoginAction(LoginFrame f) {
		this.frame = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getlErrors().setText(" ");
		String username = frame.getTfUsername().getText().trim();
		String password = new String(frame.getTfPassword().getPassword());
		
		if ("a".equals(username) && "a".equals(password)){
			System.out.println("Admin login");
			frame.setVisible(false);
			new BankForm((String)frame.getCbBank().getSelectedItem());
			
		}else{
			frame.getlErrors().setText("<html><font color=#ff0000>Invalid username or password");
		}

	}

}
