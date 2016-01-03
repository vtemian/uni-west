package v1;

import java.util.Arrays;

import v1.ContBancar.TIPCONT;

public class Client {
	public static final int NUMAR_MAX_CONTURI = 5;

	private String nume;
	private String adresa;
	private ContBancar conturi[];
	private int nrConturi = 0;

	public Client(String nume, String adresa, TIPCONT tip, String numarCont, double suma) {
		this.nume = nume;
		this.adresa = adresa;
		conturi = new ContBancar[NUMAR_MAX_CONTURI];
		addCont(tip, numarCont, suma);
	}

	public void addCont(TIPCONT tip, String numarCont, double suma) {
		ContBancar c = null;
		if (tip == ContBancar.TIPCONT.EUR)
			c = new ContEUR(numarCont, suma);
		else if (tip == ContBancar.TIPCONT.RON)
			c = new ContRON(numarCont, suma);
		conturi[nrConturi++] = c;
	}

	public ContBancar getCont(String numarcont) {
		for (int i = 0; i < nrConturi; i++) {
			if (conturi[i].getNumarCont().equals(numarcont)) {
				return conturi[i];
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "\n\tClient [nume=" + nume + ", adresa=" + adresa + ", conturi=" + Arrays.toString(conturi) + "]";
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}
}
