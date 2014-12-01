package p3.istate_engine;

import java.util.List;

public interface ActionModel {
    public List<IAction> getPossibleActions();
    public void registerAction(IAction action, ITransaction transaction);
}