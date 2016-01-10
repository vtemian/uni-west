package models;

import orm.entity.Entity;
import orm.fields.components.AutoIncrementField;
import orm.fields.components.CharField;
import orm.fields.components.IntegerField;

import java.util.Date;
import java.util.HashMap;

public class Bank extends Entity{
    public AutoIncrementField ID = new AutoIncrementField();
    public CharField bankName = new CharField("", 250);

    private HashMap<String, Client> clientsList = new HashMap<>();

    public Bank(){}

    public Bank(String[] row){
        bankName.setValue(row[0]);
    }

    public Bank(String bankName){
        this.bankName.setValue(bankName);
    }

    public void addClient(Client client) {
		clientsList.put(client.getFullName(), client);
	}

	public Client getClient(String clientName) {
		return clientsList.get(clientName);
	}

	@Override
	public String toString() {
		String clients = "";
		for (Client client : clientsList.values()) {
			clients += client.getFullName() + " ";
		}
		return "Banca [codBanca=" + getBankCode() + ", clienti=" + clients + "]";
	}

	public String getBankCode() {
		return bankName.getRawValue();
	}
}
