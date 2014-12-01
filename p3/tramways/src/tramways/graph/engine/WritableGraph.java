package tramways.graph.engine;

import tramways.graph.interfaces.IEdge;
import tramways.graph.interfaces.INode;
import tramways.graph.interfaces.IWritebleGraph;

public class WritableGraph<Node extends INode> extends Graph<Node> implements IWritebleGraph<Node>{
    @Override
    public void addNewEdge(IEdge<Node> edge) {

    }

    @Override
    public void addNewNode(Node node) {

    }

    @Override
    public void removeNode(Node node) {

    }

    @Override
    public void removeEdge(IEdge<Node> node) {

    }
}
