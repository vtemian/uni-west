package models;

import factories.AccountFactory;
import orm.entity.Entity;
import orm.fields.components.AutoIncrementField;
import orm.fields.components.CharField;
import orm.fields.components.IntegerField;

import java.util.Date;
import java.util.HashMap;

public class Client extends Entity{
    public AutoIncrementField ID = new AutoIncrementField();
    public IntegerField userID = new IntegerField(0);
    public CharField fullName = new CharField("", 250);
    public CharField address = new CharField("", 250);
    public CharField creationDate = new CharField(new Date().toString(), 250);
    private HashMap<String, BankAccount> bankAccounts = new HashMap<>();

    public Client(){}

    public Client(String[] row){
        userID.setValue(row[0]);
        fullName.setValue(row[1]);
        address.setValue(row[2]);
        creationDate.setValue(row[3]);
    }

    private Client(ClientBuilder clientBuilder) {
        this.userID.setValue(clientBuilder.userID);
        this.fullName.setValue(clientBuilder.fullName);
        this.address.setValue(clientBuilder.address);
    }

    public BankAccount addAccount(String type, String accountNumber, double balance) {
        BankAccount bankAccount = AccountFactory.createBankAccount(type, accountNumber, balance);
        bankAccounts.put(accountNumber, bankAccount);

        return bankAccount;
    }

    public BankAccount getAccount(String accountNumber) {
        return bankAccounts.get(accountNumber);
    }

    public static class ClientBuilder {
        private final String fullName;
        private String userID;
        private String address;

        public ClientBuilder(String fullName) {
            this.fullName = fullName;
        }

        public ClientBuilder userID(Integer userID) {
            this.userID = userID.toString();
            return this;
        }

        public ClientBuilder userID(AutoIncrementField userID) {
            this.userID = userID.getRawValue().toString();
            return this;
        }

        public ClientBuilder address(String address) {
            this.address = address;
            return this;
        }

        public Client build(){
            return new Client(this);
        }
    }

    public Client(Integer userID, String fullName, String address){
        this.userID.setValue(userID);
        this.fullName.setValue(fullName);
        this.address.setValue(address);
    }

    public Integer getUserID() {
        return userID.getRawValue();
    }

    public String getFullName() {
        return fullName.getRawValue();
    }

    public String getAddress() {
        return address.getRawValue();
    }

    public String getCreationDate() {
        return creationDate.getRawValue();
    }
}
