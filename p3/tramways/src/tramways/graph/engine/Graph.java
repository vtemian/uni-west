package tramways.graph.engine;

import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IEdge;
import tramways.graph.interfaces.INode;
import tramways.graph.interfaces.IGraph;

import java.util.*;

public class Graph<Node extends INode, Edge extends IEdge<Node>> implements IGraph<Node, Edge>{
    protected Map<Node, ArrayList<Edge>> edgesMap = new HashMap<Node, ArrayList<Edge>>();

    public Graph(Map<Node, ArrayList<Edge>> edgesMap) {
        this.edgesMap = edgesMap;
    }

    @Override
    public List<Node> getNodes() {
        return (List<Node>) edgesMap.keySet();
    }

    @Override
    public List<Node> getNeighbors(Node node) throws NodeNotFound{
        List<Node> neighbors = new ArrayList<Node>();

        if(!edgesMap.containsKey(node))
            throw new NodeNotFound();

        for(IEdge<Node> edges: edgesMap.get(node)){
            neighbors.add(edges.getOtherNode(node));
        }

        return neighbors;
    }

    @Override
    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<Edge>();
        for(List<Edge> edgeList: edgesMap.values()) {
            edges.addAll(edgeList);
        }
        return edges;
    }
}
