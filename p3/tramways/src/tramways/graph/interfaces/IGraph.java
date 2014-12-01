package tramways.graph.interfaces;

import java.util.List;

public interface IGraph<T>{
    public List<T> getNodes();
    public List<IEdge> getEdges();
}
