package circuit.simulator;

import circuit.application.components.OperationNode;
import circuit.application.operations.interfaces.Operation;
import graph.engine.components.Edge;
import graph.engine.components.WritableDependencyGraph;
import graph.exceptions.NodeNotFound;
import graph.exceptions.NullNodeException;

import java.util.ArrayList;
import java.util.Map;

public class Checker {
    public boolean ready(OperationNode<?> operationNode, WritableDependencyGraph<OperationNode<?>, Edge<OperationNode<?>>> graph){
        boolean ready = true;
        try {
            for(OperationNode<?> operation: graph.getNeighbors(operationNode)){
                for(Map.Entry<OperationNode<?>, ArrayList<Edge<OperationNode<?>>>> entry: graph.getEdgesMap().entrySet()){
                    if (entry.getKey().equals(operation)) ready = ready || entry.getKey().isReady();
                    for(Edge<OperationNode<?>> edge: entry.getValue()){
                        if(edge.getLeftNode().equals(operation)){
                            ready = ready || edge.getLeftNode().isReady();
                        }

                        if(edge.getRightNode().equals(operation)){
                            ready = ready || edge.getRightNode().isReady();
                        }
                    }
                }
            }
        } catch (NodeNotFound nodeNotFound) {
            nodeNotFound.printStackTrace();
        } catch (NullNodeException e) {
            e.printStackTrace();
        }
        return ready;
    }
}
