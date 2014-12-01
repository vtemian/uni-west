package tramways.graph.interfaces;

import tramways.graph.exceptions.NodeNotFound;

import java.util.List;

public interface IGraph<N>{
    public List<N> getNodes();
    public List<IEdge<N>> getEdges();
    public List<N> getNeighbors(N node) throws NodeNotFound;
}
