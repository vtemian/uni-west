package ro.uvt.bank;

public interface Operatiunii {
    double getSumaTotala();
    double getDobanda();
    void depunere(double suma) throws AccountException;
    void extragere(double suma) throws AccountException;
}
