package p3.istate_engine;

public interface ITransaction {
    public IState getStartState();
    public IState getEntState();
}
