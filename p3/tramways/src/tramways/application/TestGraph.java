package tramways.application;

import tramways.graph.engine.CostEdge;
import tramways.graph.engine.Graph;

import java.util.ArrayList;
import java.util.Map;

public class TestGraph extends Graph<Node, Segment>{
    public TestGraph(Map<Node, ArrayList<Segment>> edgesMap) {
        super(edgesMap);
    }
}