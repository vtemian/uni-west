package circuit.application.operations.bool;

import circuit.application.devices.output.CliOutputDevice;
import circuit.application.operations.interfaces.Operation;

import java.util.ArrayList;

public class AndOperation extends BoolOperation{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void run(ArrayList<Operation> operations, CliOutputDevice out) {
        for(Operation operation: operations){
            value &= operation.getValue();
        }
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
    }

    public String toString(){
        return Boolean.toString(value);
    }
}
