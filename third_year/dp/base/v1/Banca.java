package v1;

import java.util.Arrays;

public class Banca {

	private final static int MAX_CLIENTI = 100;
	private Client clienti[];
	private int nrClienti;
	private String codBanca = null;

	public Banca(String codBanca) {
		this.codBanca = codBanca;
		clienti = new Client[MAX_CLIENTI];
	}

	public void addClient(Client c) {
		clienti[nrClienti++] = c;
	}

	
	public Client getClient(String nume) {
		for (int i = 0; i < nrClienti; i++) {
			if (clienti[i].getNume().equals(nume)) {
				return clienti[i];
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return "Banca [codBanca=" + codBanca + ", clienti=" + Arrays.toString(clienti) + "]";
	}

}
