package circuit.application.operations;

import circuit.application.devices.output.CliOutputDevice;
import circuit.application.operations.interfaces.Operation;

import java.util.ArrayList;

public class ValueOperation implements Operation{
    public boolean value;

    public ValueOperation(String value) {
        this.value = Boolean.valueOf(value);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void run(ArrayList<Operation> operations, CliOutputDevice out) {

    }

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
    }
}
