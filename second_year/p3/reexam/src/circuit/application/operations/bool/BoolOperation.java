package circuit.application.operations.bool;

import circuit.application.operations.interfaces.Operation;

import java.util.ArrayList;

public abstract class BoolOperation implements Operation{
    protected boolean value;

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
