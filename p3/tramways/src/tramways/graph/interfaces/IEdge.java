package tramways.graph.interfaces;

import java.util.List;

public interface IEdge<T>{
     public List<T> getNodes();
     public T getOtherNode(T node);
}
