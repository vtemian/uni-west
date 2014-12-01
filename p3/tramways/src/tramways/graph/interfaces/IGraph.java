package tramways.graph.interfaces;

import java.util.List;

public interface IGraph<N>{
    public List<N> getNodes();
    public List<IEdge<N>> getEdges();
}
