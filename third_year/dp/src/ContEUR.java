public class ContEUR extends ContBancar {

    public ContEUR(String numarCont, double suma) {
        super(numarCont, suma);
    }

    public double getDobanda() {
        return 0.01;

    }

    @Override
    public String toString() {
        return "ContEUR [" + super.toString() + "]";
    }
}
