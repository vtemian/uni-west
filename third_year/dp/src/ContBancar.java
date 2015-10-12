public abstract class ContBancar implements Operatiunii {

    protected String numarCont = null;
    protected double suma = 0;

    public static enum TIPCONT {
        EUR, RON
    };

    protected ContBancar(String numarCont, double suma) {
        this.numarCont = numarCont;
        try {
            depunere(suma);
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getSumaTotala() {

        return suma + suma * getDobanda();
    }

    @Override
    public void depunere(double suma) throws AccountException {
        if (suma <= 0) {
            throw new AccountException("Suma depusa trebuie sa fie pozitivă.");
        }
        this.suma += suma;
    }

    @Override
    public void extragere(double suma) throws AccountException {
        if (suma <= 0) {
            throw new AccountException("Suma extrasă trebuie sa fie pozitivă.");
        }

        if (suma > this.suma) {
            throw new AccountException("Suma extrasă [" + suma + "] trebuie sa fie mai mică decât cea existentă [" + this.suma + "].");
        }

        this.suma -= suma;
    }

    public String toString() {
        return "numarCont=" + numarCont + ", suma=" + suma;
    }

    public String getNumarCont() {
        return numarCont;
    }

}
