package circuit.simulator;

import circuit.application.components.OperationNode;
import circuit.application.devices.output.CliOutputDevice;
import circuit.application.operations.ValueOperation;
import circuit.application.operations.bool.BoolOperation;
import circuit.application.operations.interfaces.Operation;
import graph.engine.components.Edge;
import graph.engine.components.WritableDependencyGraph;
import graph.exceptions.NodeNotFound;
import graph.exceptions.NullNodeException;

import java.util.ArrayList;
import java.util.Map;

public class Worker implements Runnable {
    private Thread t;
    private WritableDependencyGraph<OperationNode<?>, Edge<OperationNode<?>>> graph;
    private ArrayList<OperationNode<?>> operationNodes;
    private Checker checker;
    private CliOutputDevice out;

    public Worker(ArrayList<OperationNode<?>> operationNodes, WritableDependencyGraph<OperationNode<?>, Edge<OperationNode<?>>> graph, CliOutputDevice out) {
        this.graph = graph;
        this.operationNodes = operationNodes;
        this.checker = new Checker();
        this.out = out;
    }

    @Override
    public void run() {
        for(OperationNode<?> operationNode: operationNodes) {
            if(operationNode.getOperation().getClass().equals(ValueOperation.class))
                continue;

            while(!checker.ready(operationNode, graph)) {
                try {
                    out.print("wait for " + operationNode.getName());
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            out.print(operationNode.getName() + " is ready!");
            Operation operation;
            try {
                ArrayList<Operation> arguments = new ArrayList<Operation>();
                for(OperationNode<?> dependable: graph.getNeighbors(operationNode)) {
                    arguments.add(dependable.getOperation());
                }

                operation = operationNode.getOperation();
                operation.run(arguments, out);

                operationNode.setReady();

                out.print(operationNode.getName() + " done with value: " + operation.toString());
            } catch (NodeNotFound nodeNotFound) {
                nodeNotFound.printStackTrace();
            } catch (NullNodeException e) {
                e.printStackTrace();
            }
        }
    }

    public void start()
    {
        if (t == null)
        {
            t = new Thread(this, "new Thread");
            t.start();
        }
    }
}
