package tramways.graph.engine;

import tramways.graph.interfaces.IEdge;
import tramways.graph.interfaces.INode;
import tramways.graph.interfaces.IGraph;

import java.util.List;

public class Graph<Node extends INode> implements IGraph<Node>{
    protected List<Node> nodes;
    protected List<IEdge<Node>> edges;

    @Override
    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public List<IEdge<Node>> getEdges() {
        return edges;
    }
}
