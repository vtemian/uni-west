package gui.actions.client;

import gui.bankClientsManagement.NewClientWindow;
import models.Client;
import models.User;
import orm.components.ORM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClientAction implements ActionListener{
    private NewClientWindow clientWindow;

    public CreateClientAction(NewClientWindow clientWindow) {
        this.clientWindow = clientWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String fullName = clientWindow.getTfName().getText().trim();
        String address = clientWindow.getTfAddress().getText().trim();
        String bankAccountType = (String) clientWindow.getCbAccountType().getSelectedItem();
        String initialSum = clientWindow.getTfSum().getText().trim();
        String emailAddress = clientWindow.getTfEmailAddress().getText().trim();

        ORM orm = ORM.getInstance();
        User user = User.generateFromEmail(emailAddress);
        orm.create(user);

        user = (User) orm.retrieve(User.class).where("username", user.username.getRawValue()).first();
        Client client = new Client(user.ID.getRawValue(), fullName, address);
        orm.create(client);

        clientWindow.setVisible(false);
    }
}
