package v1;

public class ContRON extends ContBancar implements Transfer {

	public ContRON(String numarCont, double suma) {
		super(numarCont, suma);
	}

	public double getDobanda() {
		if (suma < 500)
			return 0.03;
		else
			return 0.08;

	}

	@Override
	public String toString() {
		return "ContRON [" + super.toString() + "]";
	}

	@Override
	public void Transfer(ContBancar c, double s) {
		c.extragere(s);
		depunere(s);
	}
}
