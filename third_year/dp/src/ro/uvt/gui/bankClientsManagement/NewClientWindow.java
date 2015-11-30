package ro.uvt.gui.bankClientsManagement;

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

import ro.uvt.gui.GUICommons;

public class NewClientWindow extends JDialog {

	private JTextField tfNume = null;
	private JLabel lErrNume = null;
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

	private void initGUI() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		GridLayout gl = new GridLayout(4, 3);
		gl.setVgap(3);
		JPanel pInput = new JPanel(gl);
		JLabel label = new JLabel("Name: ");
		pInput.add(label);
		tfNume = new JTextField(GUICommons.TEXT_FIELD_LENGHT);
		pInput.add(tfNume);
		lErrNume = new JLabel(" ");
		pInput.add(lErrNume);

		label = new JLabel("Address: ");
		pInput.add(label);
		tfAddress = new JTextField(GUICommons.TEXT_FIELD_LENGHT);
		pInput.add(tfAddress);
		pInput.add(new JLabel());

		label = new JLabel("Account type: ");
		pInput.add(label);
		String stypes[] = { "RON", "EUR" };
		cbAccountType = new JComboBox<String>(stypes);
		pInput.add(cbAccountType);
		pInput.add(new JLabel());

		label = new JLabel("Intial sum: ");
		pInput.add(label);
		tfSum = new JTextField(GUICommons.TEXT_FIELD_LENGHT);
		pInput.add(tfSum);
		lErrSum = new JLabel(" ");
		pInput.add(lErrSum);

		add(pInput);

		JPanel pButtons = new JPanel();
		bSave = new JButton("Save");
		pButtons.add(bSave);
		bCancel = new JButton("Cancel");
		pButtons.add(bCancel);
		add(pButtons);

	}

}
