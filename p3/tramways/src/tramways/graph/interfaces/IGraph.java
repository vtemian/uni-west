package tramways.graph.interfaces;

import tramways.graph.exceptions.NodeNotFound;

import java.util.ArrayList;
import java.util.List;

public interface IGraph<N, Edge>{
    public List<N> getNodes();
    public List<Edge> getEdges();
    public ArrayList<N> getNeighbors(N node) throws NodeNotFound;
}
