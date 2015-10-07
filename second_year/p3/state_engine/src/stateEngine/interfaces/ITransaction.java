package stateEngine.interfaces;

public interface ITransaction {
    public IState getStartState();
    public IState getEntState();
}
