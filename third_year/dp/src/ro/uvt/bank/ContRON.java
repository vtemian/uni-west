package ro.uvt.bank;

public class ContRON extends ContBancar implements Transfer {

    public ContRON(String numarCont, double suma) throws AccountException {
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
    public void Transfer(ContBancar c, double s) throws AccountException {
        c.extragere(s);
        depunere(s);
    }
}
