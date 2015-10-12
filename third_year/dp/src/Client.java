import java.util.Arrays;
import java.util.HashMap;

public class Client {
    public static final int NUMAR_MAX_CONTURI = 5;

    private String nume;
    private String adresa;
    private HashMap<String, ContBancar> conturi;
    private int nrConturi = 0;

    public Client(String nume, String adresa, ContBancar.TIPCONT tip, String numarCont, double suma) {
        this.nume = nume;
        this.adresa = adresa;
        this.conturi = new HashMap<String, ContBancar>();

        try {
            addCont(tip, numarCont, suma);
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }

    public void addCont(ContBancar.TIPCONT tip, String numarCont, double suma) throws AccountException {
        ContBancar c = null;
        if (tip == ContBancar.TIPCONT.EUR)
            c = new ContEUR(numarCont, suma);
        else if (tip == ContBancar.TIPCONT.RON)
            c = new ContRON(numarCont, suma);
        else
            throw new AccountException("Tipul contului trebuie sa fie EUR sau RON, nu [" + tip +"]");

        if (nrConturi == NUMAR_MAX_CONTURI)
            throw new AccountException("Ai atins limita maximă de conturi [" + NUMAR_MAX_CONTURI + "]");

        // TODO: Add account to accounts set
        conturi.put(numarCont, c);
    }

    public ContBancar getCont(String numarcont) {
        return conturi.get(numarcont);
    }

    @Override
    public String toString() {
        return "\n\tClient [nume=" + nume + ", adresa=" + adresa + ", conturi=" + conturi.size() + "]";
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
