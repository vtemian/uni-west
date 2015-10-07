package tramways.graph.engine.operations;

import tramways.graph.alghoritms.RouteGraphAlgorithm;
import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.INode;
import tramways.graph.interfaces.IGraph;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public abstract class Operation<Strategy extends RouteGraphAlgorithm, Node extends INode> {
    private Strategy strategy;
    private IGraph graph;
    private Map<Node, ArrayList<Node>> routesMap = new HashMap<Node, ArrayList<Node>>();

    public Operation(Strategy strategy, IGraph graph) {
        this.strategy = strategy;
        this.graph = graph;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public abstract ArrayList<ArrayList<Node>> computeRoute(Node startNode, Node endNode) throws NodeNotFound;

}
