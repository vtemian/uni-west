package stateEngine.componentes;

import graph.interfaces.IGraph;
import stateEngine.interfaces.IState;
import stateEngine.interfaces.IStateChangeListener;
import stateEngine.interfaces.IStateEngine;
import stateEngine.interfaces.ITransaction;
import javax.transaction.InvalidTransactionException;
import java.util.List;

public class StateEngine implements IStateEngine{
    private IState state;
    private IGraph graph;

    public StateEngine(IState state, IGraph graph) {
        this.state = state;
        this.graph = graph;
    }

    @Override
    public void setState(IState state) {
        this.state = state;
    }

    @Override
    public IState getState(IState state) {
        return state;
    }

    @Override
    public IState executeTransaction(ITransaction transaction) throws InvalidTransactionException {
        return null;
    }

    @Override
    public List<ITransaction> getPossibleTransactions(IState state) {
        return null;
    }

    @Override
    public IStateChangeListener registerListener(IStateChangeListener listener) {
        return null;
    }

    @Override
    public IStateChangeListener unregisterListener(IStateChangeListener listener) {
        return null;
    }
}
