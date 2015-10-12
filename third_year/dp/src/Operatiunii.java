public interface Operatiunii {
    public double getSumaTotala();
    public double getDobanda();
    public void depunere(double suma) throws AccountException;
    public void extragere(double suma) throws AccountException;
}
