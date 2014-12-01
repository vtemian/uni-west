package tramways.graph.interfaces;

public interface IWritebleGraph<N> extends IGraph<N>{
    public void addNewEdge(IEdge<N> edge);
    public void addNewNode(N node);
    public void removeNode(N node);
    public void removeEdge(IEdge<N> node);
}
