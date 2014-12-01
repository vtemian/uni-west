package tramways.graph.interfaces;

public interface IWritebleGraph<T> extends IGraph<T>{
    public void addNewEdge(IEdge<T> edge);
    public void addNewNode(T node);
    public void removeNode(T node);
    public void removeEdge(IEdge<T> node);
}
