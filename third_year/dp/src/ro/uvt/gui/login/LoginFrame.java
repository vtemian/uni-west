package ro.uvt.gui.login;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ro.uvt.gui.GUICommons;

public class LoginFrame extends JFrame {
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JComboBox<String> cbBank;
	private JButton bLogin = null;
	private JLabel lErrors = null;

	public LoginFrame() {
		super("Login in your bank");

		initGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}

	private void initGUI() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		JPanel pForm = new JPanel();
		pForm.setLayout(new GridLayout(3, 2));
		JLabel l = new JLabel("Username: ");
		pForm.add(l);
		tfUsername = new JTextField(GUICommons.TEXT_FIELD_LENGHT);
		pForm.add(tfUsername);

		l = new JLabel("Password: ");
		pForm.add(l);
		tfPassword = new JPasswordField(GUICommons.TEXT_FIELD_LENGHT);
		pForm.add(tfPassword);

		l = new JLabel("Bank: ");
		pForm.add(l);

		String[] banks = { "BRD", "BTRL", "BCR", "CEC" };
		cbBank = new JComboBox<String>(banks);
		pForm.add(cbBank);

		this.add(pForm);

		JPanel pButtons = new JPanel();
		bLogin = new JButton("Login");
		bLogin.addActionListener(new LoginAction(this));
		pButtons.add(bLogin);
		this.add(pButtons);
		
		JPanel panel = new JPanel();
		lErrors= new JLabel(" ");
		panel.add(lErrors);
		this.add(panel);
	}

	public static void main(String[] args) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					new LoginFrame();

				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the tfUsername
	 */
	public final JTextField getTfUsername() {
		return tfUsername;
	}

	/**
	 * @return the tfPassword
	 */
	public final JPasswordField getTfPassword() {
		return tfPassword;
	}

	/**
	 * @return the cbBank
	 */
	public final JComboBox<String> getCbBank() {
		return cbBank;
	}

	/**
	 * @return the lErrors
	 */
	public final JLabel getlErrors() {
		return lErrors;
	}
}
