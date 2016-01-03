import java.util.HashMap;

public class Client {
	private String UUID;
	private String clientName;
	private String clientAddress;
	private HashMap<String, BankAccount> bankAccounts;

	public Client(String clientName, String clientAddress, BankAccount.TYPE type, String accountNumber, double balance) {
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		bankAccounts = new HashMap<>();
		addCont(type, accountNumber, balance);
	}

	public void addCont(BankAccount.TYPE type, String accountNumber, double balance) {
		BankAccount bankAccount = null;
		if (type == BankAccount.TYPE.EUR)
			bankAccount = new ContEUR(accountNumber, balance);
		else if (type == BankAccount.TYPE.RON)
			bankAccount = new ContRON(accountNumber, balance);

		bankAccounts.put(accountNumber, bankAccount);
	}

	public BankAccount getCont(String accountNumber) {
		return bankAccounts.get(accountNumber);
	}

	public String getUUID() {
		return UUID;
	}

	@Override
	public String toString() {
		String accounts = "";
		for (BankAccount bankAccount: bankAccounts.values()) {
			accounts += " { "+ bankAccount.getAccountNumber() +" } ";
		}
		return "\n\tClient [name=" + clientName + ", address=" + clientAddress + ", accounts=" + accounts + "]";
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
