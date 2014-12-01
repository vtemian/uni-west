package tramways.graph;

public interface WritableCostEdge<T> extends ICostEdge<T>, IWritableEdge<T>{
    public void setCost();
}
