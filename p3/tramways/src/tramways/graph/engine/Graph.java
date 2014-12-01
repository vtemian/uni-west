package tramways.graph.engine;

import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IEdge;
import tramways.graph.interfaces.INode;
import tramways.graph.interfaces.IGraph;

import java.util.*;

public class Graph<Node extends INode> implements IGraph<Node>{
    protected List<Node> nodes;
    protected Map<Node, ArrayList<IEdge<Node>>> edgesMap = new HashMap<Node, ArrayList<IEdge<Node>>>();

    @Override
    public List<Node> getNodes() {
        return nodes;
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
    public List<IEdge<Node>> getEdges() {
        List<IEdge<Node>> edges = new ArrayList<IEdge<Node>>();
        for(List<IEdge<Node>> edgeList: edgesMap.values()) {
            edges.addAll(edgeList);
        }
        return edges;
    }
}
