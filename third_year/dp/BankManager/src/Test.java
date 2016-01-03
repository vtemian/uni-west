public class Test {

	public static void main(String[] args) {
		/**
		 * Creare banca BCR cu 2 clienti
		 */
		Bank bcr = new Bank("Bank BCR");
		// creare client Ionescu cu 2 conturi unul in EUR si unul in RON
		Client cl1 = new Client("Ionescu Ion", "Timisoara", BankAccount.TYPE.EUR, "EUR124", 200.9);
		bcr.addClient(cl1);
		cl1.addCont(BankAccount.TYPE.RON, "RON1234", 400);
		// creare client Marinecu cu un cont in RON
		Client cl2 = new Client("Marinescu Marin", "Timisoara", BankAccount.TYPE.RON, "RON126", 100);
		bcr.addClient(cl2);
		System.out.println(bcr);

		/**
		 * Creare banca CEC cu un client
		 */
		Bank cec = new Bank("Bank CEC");
		Client clientCEC = new Client("Vasilescu Vasile", "Brasov", BankAccount.TYPE.EUR, "EUR128", 700);
		cec.addClient(clientCEC);
		System.out.println(cec);

		// deposita in CONT RON126 a clientului Marinescu
		Client cl = bcr.getClient("Marinescu Marin");
		if (cl != null) {
			cl.getCont("RON126").deposit(400);
			System.out.println(cl);
		}

		// extragre in CONT RON126 a clientului Marinescu
		if (cl != null) {
			cl.getCont("RON126").withdrawal(67);
			System.out.println(cl);
		}

		// tranfer intre conturile in lei RON126 si RON1234
		ContRON c1 = (ContRON) cl.getCont("RON126");
		ContRON c2 = (ContRON) bcr.getClient("Ionescu Ion").getCont("RON1234");
		c1.Transfer(c2, 40);
		System.out.println(bcr);

	}
}
