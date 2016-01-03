public class ContEUR extends BankAccount {

	public ContEUR(String accountNumber, double balance) {
		super(accountNumber, balance);
	}

	public double getInterest() {
		return 0.01;
	}

	@Override
	public String toString() {
		return "Euro Account [" + super.toString() + "]";
	}
}
