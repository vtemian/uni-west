package tramways.graph.interfaces;

public interface IWritableCostEdge<T> extends ICostEdge<T>, IWritableEdge<T>{
    public void setCost(Float cost);
}
