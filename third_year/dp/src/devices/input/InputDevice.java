package devices.input;

public abstract class InputDevice implements IInputDevice{
    public enum ACTIONS {
        EXIT,
    }

    public ACTIONS getAction() {
        return ACTIONS.EXIT;
    }
}
