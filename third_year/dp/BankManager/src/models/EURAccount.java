package models;

public class EURAccount extends BankAccount {

    public EURAccount(){}

	public EURAccount(String accountNumber, double balance) {
        this.accountNumber.setValue(accountNumber);
        this.balance.setValue((float)balance);
	}

	@Override
    public double getInterest() {
		return 0.01;
	}

	@Override
	public String toString() {
		return "Euro Account [" + super.toString() + "]";
	}
}
