public abstract class BankAccount implements Operations {

	protected String accountNumber = null;
	protected double balance = 0;

	public static enum TYPE {
		EUR, RON
	}

	protected BankAccount(String accountNumber, double balance) {
		this.accountNumber = accountNumber;
		deposit(balance);
	}

	@Override
	public double getBalance() {
		return balance + balance * getInterest();
	}

	@Override
	public void deposit(double amount) {
		balance += amount;
	}

	@Override
	public void withdrawal(double amount) {
		balance -= amount;
	}

	public String toString() {
		return "accountNumber=" + accountNumber + ", balance=" + balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}
