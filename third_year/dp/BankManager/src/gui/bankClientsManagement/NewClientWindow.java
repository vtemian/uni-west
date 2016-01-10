package gui.bankClientsManagement;

import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.GUICommons;
import gui.actions.client.CreateClientAction;

public class NewClientWindow extends JDialog {

	private JTextField tfName = null;
	private JLabel lErrName = null;
	private JTextField tfAddress = null;
	private JTextField tfSum = null;
	private JLabel lErrSum = null;
	private JComboBox<String> cbAccountType = null;
	private JButton bSave = null;
	private JButton bCancel = null;

	public NewClientWindow(JFrame parent) {
		super(parent, Dialog.ModalityType.APPLICATION_MODAL);
		this.setTitle("Add new client");
		this.setLocationRelativeTo(parent);
		initGUI();
		this.pack();
		this.setVisible(true);
	}

    public JTextField getTfName() {
        return tfName;
    }

    public JTextField getTfAddress() {
        return tfAddress;
    }

    public JTextField getTfSum() {
        return tfSum;
    }

    public JComboBox<String> getCbAccountType() {
        return cbAccountType;
    }

    private void initGUI() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		GridLayout gl = new GridLayout(4, 3);
		gl.setVgap(3);
		JPanel pInput = new JPanel(gl);
		JLabel label = new JLabel("Name: ");
		pInput.add(label);
		tfName = new JTextField(GUICommons.TEXT_FIELD_LENGHT);
		pInput.add(tfName);
		lErrName = new JLabel(" ");
		pInput.add(lErrName);

		label = new JLabel("Address: ");
		pInput.add(label);
		tfAddress = new JTextField(GUICommons.TEXT_FIELD_LENGHT);
		pInput.add(tfAddress);
		pInput.add(new JLabel());

		label = new JLabel("Account type: ");
		pInput.add(label);
		String types[] = { "RON", "EUR" };
		cbAccountType = new JComboBox<String>(types);
		pInput.add(cbAccountType);
		pInput.add(new JLabel());

		label = new JLabel("Initial sum: ");
		pInput.add(label);
		tfSum = new JTextField(GUICommons.TEXT_FIELD_LENGHT);
		pInput.add(tfSum);
		lErrSum = new JLabel(" ");
		pInput.add(lErrSum);

		add(pInput);

		JPanel pButtons = new JPanel();
		bSave = new JButton("Save");
        bSave.addActionListener(new CreateClientAction(this));
		pButtons.add(bSave);

		bCancel = new JButton("Cancel");
		pButtons.add(bCancel);
		add(pButtons);

	}

}
