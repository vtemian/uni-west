package ro.uvt.bank;

public interface Transfer {
    public void Transfer(ContBancar c, double s) throws AccountException;
}
