package circuit.application.operations.interfaces;

import circuit.application.components.OperationNode;
import circuit.application.devices.output.CliOutputDevice;

import java.util.ArrayList;

public interface Operation {
    public String getName();
    public void run(ArrayList<Operation> operations, CliOutputDevice out);
    public boolean getValue();
    public void setValue(boolean value);
}
