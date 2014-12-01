package p3.istate_engine;

public interface IStateChangeListener {
    public void statusChanged(IState oldState, IState newState, ITransaction transaction);
}
