package circuit.simulator;

import circuit.application.components.OperationNode;
import circuit.application.devices.input.CliInputDevice;
import circuit.application.devices.output.CliOutputDevice;
import circuit.application.operations.interfaces.Operation;
import graph.engine.components.Edge;
import graph.engine.components.WritableDependencyGraph;

import java.util.ArrayList;
import java.util.Collections;

public class Simulator {
    private CliInputDevice in;
    private CliOutputDevice out;

    public Simulator(CliInputDevice in, CliOutputDevice out) {
        this.in = in;
        this.out = out;
    }

    public void simulate(ArrayList<ArrayList<OperationNode<?>>> operations, WritableDependencyGraph<OperationNode<?>, Edge<OperationNode<?>>> graph){
        for(ArrayList<OperationNode<?>> operationNodes: operations){
            Collections.reverse(operationNodes);
            Worker worker = new Worker(operationNodes, graph, out);
            worker.start();
        }
    }
}
