public class ContRON extends BankAccount implements Transfer {

	public ContRON(String accountNumber, double balance) {
		super(accountNumber, balance);
	}

	public double getInterest() {
		if (balance < 500)
			return 0.03;
		else
			return 0.08;
	}

	@Override
	public String toString() {
		return "RON Account [" + super.toString() + "]";
	}

	@Override
	public void Transfer(BankAccount bankAccount, double amount) {
		bankAccount.withdrawal(amount);
		deposit(amount);
	}
}
