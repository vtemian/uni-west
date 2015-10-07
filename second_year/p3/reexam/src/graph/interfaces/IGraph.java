package graph.interfaces;

import graph.exceptions.NodeNotFound;
import graph.exceptions.NullNodeException;

import java.util.ArrayList;
import java.util.List;

public interface IGraph<Node, Edge>{
    public List<Node> getNodes();
    public List<Edge> getEdges();
    public ArrayList<Node> getNeighbors(Node node) throws NodeNotFound, NullNodeException;
    public ArrayList<Edge> getNeighborsEdge(Node node) throws NodeNotFound, NullNodeException;
}
