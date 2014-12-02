package tramways.graph.interfaces;

public interface ICostEdge<Node> extends IEdge<Node> {
    public Float getCost();
    public Node getLeftNode();
    public Node getRightNode();
}