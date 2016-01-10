package models;

import orm.entity.Entity;
import orm.fields.components.AutoIncrementField;
import orm.fields.components.CharField;
import orm.fields.components.FloatField;

import java.util.HashMap;

public class BankAccount extends Entity implements Operations, Transfer{
    public AutoIncrementField ID = new AutoIncrementField();
    public CharField accountNumber = new CharField("", 250);
    public FloatField balance = new FloatField((float) 0.0);

    public BankAccount(){}

    public BankAccount(String accountNumber, float balance){
        this.accountNumber.setValue(accountNumber);
        this.balance.setValue(balance);
    }


	@Override
	public double getBalance() {
        float currentBalance = this.balance.getRawValue();
		return currentBalance + currentBalance * getInterest();
	}

    @Override
    public double getInterest() {
        return 0;
    }

    @Override
	public void deposit(double amount) {
        float currentBalance = this.balance.getRawValue();
        this.balance.setValue(currentBalance + amount);
	}

	@Override
	public void withdrawal(double amount) {
        float currentBalance = this.balance.getRawValue();
        this.balance.setValue(currentBalance - amount);
	}

    @Override
	public void Transfer(BankAccount bankAccount, double amount) {
		bankAccount.withdrawal(amount);
		deposit(amount);
	}
}
