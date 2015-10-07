package graph.engine.components;

import graph.exceptions.NodeNotFound;
import graph.exceptions.NullNodeException;
import graph.interfaces.IEdge;
import graph.interfaces.IGraph;

import java.util.*;

public class Graph<Node extends SimpleNode, edge extends Edge<Node>> implements IGraph<Node, edge>{
    public Map<Node, ArrayList<edge>> getEdgesMap() {
        return edgesMap;
    }

    protected Map<Node, ArrayList<edge>> edgesMap = new HashMap<Node, ArrayList<edge>>();

    public Graph(Map<Node, ArrayList<edge>> edgesMap) {
        this.edgesMap = edgesMap;
    }

    @Override
    public List<Node> getNodes() {
        return (List<Node>) edgesMap.keySet();
    }

    @Override
    public ArrayList<Node> getNeighbors(Node node) throws NodeNotFound, NullNodeException {
        ArrayList<Node> neighbors = new ArrayList<Node>();

        if(node == null)
            throw new NullNodeException();

        if(!edgesMap.containsKey(node))
            throw new NodeNotFound();

        for(IEdge<Node> edges: edgesMap.get(node)){
            neighbors.add(edges.getOtherNode(node));
        }

        return neighbors;
    }

    @Override
    public ArrayList<edge> getNeighborsEdge(Node node) throws NodeNotFound, NullNodeException {
        ArrayList<edge> neighbors = new ArrayList<edge>();

        if(node == null)
            throw new NullNodeException();

        if(!edgesMap.containsKey(node))
            throw new NodeNotFound();

        return edgesMap.get(node);
    }

    @Override
    public List<edge> getEdges() {
        List<edge> edges = new ArrayList<edge>();
        for(List<edge> edgeList: edgesMap.values()) {
            edges.addAll(edgeList);
        }
        return edges;
    }
}
