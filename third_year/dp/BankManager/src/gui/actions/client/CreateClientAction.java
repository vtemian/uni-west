package gui.actions.client;

import gui.bankClientsManagement.NewClientWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClientAction implements ActionListener{
    private NewClientWindow clientWindow;

    public CreateClientAction(NewClientWindow clientWindow) {
        this.clientWindow = clientWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }
}
