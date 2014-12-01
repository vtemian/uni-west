package tramways.graph.interfaces;

public interface IWritebleGraph<Node, Edge> extends IGraph<Node, Edge>{
    public void addNewEdge(Edge edge);
    public void addNewNode(Node node);
    public void removeNode(Node node);
    public void removeEdge(Edge node);
}
