package graph.engine.operations;

import graph.alghoritms.RouteGraphAlgorithm;
import graph.exceptions.NodeNotFound;
import graph.interfaces.IGraph;
import graph.interfaces.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
