package tramways.graph.interfaces;

public interface ICostEdge<T> extends IEdge<T> {
    Float cost = new Float(0.0);
    public Float getCost();
}