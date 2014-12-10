package graph.engine.components;

import graph.exceptions.NodeNotFound;
import graph.exceptions.NullNodeException;
import graph.interfaces.IEdge;
import graph.interfaces.IGraph;
import graph.interfaces.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ArrayList<Edge> getNeighborsEdge(Node node) throws NodeNotFound, NullNodeException {
        ArrayList<Edge> neighbors = new ArrayList<Edge>();

        if(node == null)
            throw new NullNodeException();

        if(!edgesMap.containsKey(node))
            throw new NodeNotFound();

        return edgesMap.get(node);
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
