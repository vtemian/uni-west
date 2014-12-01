package tramways.graph.interfaces;

public interface IWritableEdge<T> extends IEdge<T> {
    public void setNodes(T left, T rigth);
}