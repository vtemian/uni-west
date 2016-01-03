import java.util.HashMap;

public class Bank {

	private HashMap<String, Client> clientsList;
	private String bankCode = null;

	public Bank(String bankCode) {
		this.bankCode = bankCode;
		clientsList = new HashMap<>();
	}

	public void addClient(Client client) {
		clientsList.put(client.getClientName(), client);
	}

	public Client getClient(String clientName) {
		return clientsList.get(clientName);
	}

	@Override
	public String toString() {
		String clients = "";
		for (Client client : clientsList.values()) {
			clients += client.getClientName() + " ";
		}
		return "Banca [codBanca=" + bankCode + ", clienti=" + clients + "]";
	}
}
