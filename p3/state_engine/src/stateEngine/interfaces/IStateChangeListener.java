package stateEngine.interfaces;

public interface IStateChangeListener {
    public void statusChanged(IState oldState, IState newState, ITransaction transaction);
}
