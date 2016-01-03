package v1;

public abstract class ContBancar implements Operatiunii {

	protected String numarCont = null;
	protected double suma = 0;

	public static enum TIPCONT {
		EUR, RON
	};

	protected ContBancar(String numarCont, double suma) {
		this.numarCont = numarCont;
		depunere(suma);
	}

	@Override
	public double getSumaTotala() {

		return suma + suma * getDobanda();
	}

	@Override
	public void depunere(double suma) {

		this.suma += suma;
	}

	@Override
	public void extragere(double suma) {

		this.suma -= suma;
	}

	public String toString() {
		return "numarCont=" + numarCont + ", suma=" + suma;
	}

	public String getNumarCont() {
		return numarCont;
	}

}
