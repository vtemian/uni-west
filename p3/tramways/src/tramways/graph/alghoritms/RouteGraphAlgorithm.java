package tramways.graph.alghoritms;

import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IEdge;
import tramways.graph.interfaces.IGraph;
import tramways.graph.interfaces.INode;

import java.util.ArrayList;
import java.util.Map;

public interface RouteGraphAlgorithm<Node extends INode, Edge extends IEdge<Node>, CostType extends Number> {
    public Map<Node, ArrayList<Node>>  compute(Node startNode, Node endNode) throws NodeNotFound;
    public IGraph getGraph();
    public void setGraph(IGraph graph);
}
