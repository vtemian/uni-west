package circuit.application.components;

import circuit.application.operations.interfaces.Operation;
import graph.engine.components.SimpleNode;

public class OperationNode<O extends Operation> extends SimpleNode implements Comparable{
    private O operation;
    private boolean ready = false;

    public O getOperation() {
        return operation;
    }

    public void setOperation(O operation) {
        this.operation = operation;
    }

    public void setReady() {
        ready = true;
    }

    public boolean isReady(){
        return ready;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        String that = o.toString();

        if (getName() != null ? !getName().equals(that) : that != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public OperationNode(String name, O operation) {
        super(name);
        this.operation = operation;
    }

    public void setValue(boolean value){
        System.out.println("pula mea " + value);
        operation.setValue(value);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
