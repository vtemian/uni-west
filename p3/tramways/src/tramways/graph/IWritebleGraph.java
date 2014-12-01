package tramways.graph;

public interface IWritebleGraph extends IGraph{
    public void addNewEdge(IEdge edge);
    public void addNewNode(Node node);
    public void removeNode(Node node);
    public void removeEdge(Node node);
}
