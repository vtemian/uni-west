package gui.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BankForm extends JFrame {

	private JMenuItem menuCreateClient;

	public BankForm(String bankName) {
		super("Welcome to " + bankName);

		initGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	private void initGUI() {
		JMenuBar menuBar = new JMenuBar();
		JMenu mAccount = new JMenu("Account");
		menuCreateClient = new JMenuItem("Create Client");
		menuCreateClient.addActionListener(new MenuAction(MenuAction.ACTION_NEW_CLIENT, this));
		mAccount.add(menuCreateClient);
		JMenuItem mItem = new JMenuItem("Transfer money");
		mAccount.add(mItem);

		menuBar.add(mAccount);
		JMenu mReports = new JMenu("Reports");
		mItem = new JMenuItem("Account Activity");
		mReports.add(mItem);
		mItem = new JMenuItem("Daily Report");
		mReports.add(mItem);
		menuBar.add(mReports);
		
		JMenu mHelp = new JMenu("Help");
		mItem = new JMenuItem("About");
		mHelp.add(mItem);
		menuBar.add(mHelp);
		this.setJMenuBar(menuBar);

		// this.add(new JPanel());
		createContentPane();
	}
	
	public void createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
 
        //Create a scrolled text area.
        JTextArea output = new JTextArea(30, 100);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);
 
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
 
        this.add( contentPane);
    }
}


