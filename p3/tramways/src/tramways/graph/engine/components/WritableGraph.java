package tramways.graph.engine.components;

import tramways.graph.interfaces.IEdge;
import tramways.graph.interfaces.INode;
import tramways.graph.interfaces.IWritebleGraph;

import java.util.ArrayList;
import java.util.Map;

public class WritableGraph<Node extends SimpleNode, Edge extends CostEdge<Node>> extends Graph<Node, Edge> implements IWritebleGraph<Node, Edge>{
    public WritableGraph(Map<Node, ArrayList<Edge>> edgesMap) {
        super(edgesMap);
    }

    @Override
    public void addNewEdge(Edge edge) {

    }

    @Override
    public void addNewNode(Node node) {

    }

    @Override
    public void removeNode(Node node) {

    }

    @Override
    public void removeEdge(Edge node) {

    }
}
