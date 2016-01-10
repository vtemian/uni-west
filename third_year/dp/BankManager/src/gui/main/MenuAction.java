package gui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.bankClientsManagement.NewClientWindow;


public class MenuAction implements ActionListener {

	public static final String ACTION_NEW_CLIENT = "new client";

	private String action;
	private JFrame frame = null;

	public MenuAction(String action, JFrame frame) {
		this.action = action;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (action) {
		case ACTION_NEW_CLIENT:
			System.out.println("New client window");
			new NewClientWindow(frame);
			break;
		default:
			System.out.println("Not implemented action: " + action);
		}

	}

}
