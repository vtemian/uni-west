package models;

public class RONAccount extends BankAccount {
    public RONAccount(){}
    public RONAccount(String accountNumber, double balance) {
        super(accountNumber, (float)balance);
        this.accountNumber.setValue(accountNumber);
        this.balance.setValue((float)balance);
	}

	@Override
	public double getInterest() {
		if (balance.getRawValue() < 500)
			return 0.03;
		else
			return 0.08;
	}

	@Override
	public String toString() {
		return "RON Account [" + super.toString() + "]";
	}
}
