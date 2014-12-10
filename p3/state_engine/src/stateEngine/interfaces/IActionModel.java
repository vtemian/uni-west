package stateEngine.interfaces;

import java.util.List;

public interface IActionModel {
    public List<IAction> getPossibleActions();
    public void registerAction(IAction action, ITransaction transaction);
}