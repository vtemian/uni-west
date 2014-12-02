package tramways.graph.alghoritms;

import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IGraph;
import tramways.graph.interfaces.INode;

import java.util.ArrayList;

public abstract class RouteGraphAlgorithm {
    protected IGraph graph;

    public RouteGraphAlgorithm(IGraph graph) {
        this.graph = graph;

    }

    public IGraph getGraph() {
        return graph;
    }

    public void setGraph(IGraph graph) {
        this.graph = graph;
    }

}
