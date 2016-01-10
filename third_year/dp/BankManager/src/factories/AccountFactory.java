package factories;

import models.BankAccount;
import models.EURAccount;
import models.RONAccount;

public class AccountFactory {
    public static BankAccount createBankAccount(String currency, String accountNumber, double balance) {
        BankAccount bankAccount = null;

        if (currency.equals("EUR")) {
            bankAccount = new EURAccount(accountNumber, balance);
        } else if (currency.equals("RON")) {
            bankAccount = new RONAccount(accountNumber, balance);
        }

        return bankAccount;
    }
}
