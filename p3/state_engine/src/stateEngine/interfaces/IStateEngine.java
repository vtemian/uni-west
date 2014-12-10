package stateEngine.interfaces;

import javax.transaction.InvalidTransactionException;
import java.util.List;

public interface IStateEngine {
    public void setState(IState state);
    public IState getState(IState state);
    public IState executeTransaction(ITransaction transaction) throws InvalidTransactionException;
    public List<ITransaction> getPossibleTransactions(IState state);
    public IStateChangeListener registerListener(IStateChangeListener listener);
    public IStateChangeListener unregisterListener(IStateChangeListener listener);
}
