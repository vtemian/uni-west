package tramways.application;

import tramways.graph.engine.components.Graph;

import java.util.ArrayList;
import java.util.Map;

public class TestGraph extends Graph<Node, Segment>{
    public TestGraph(Map<Node, ArrayList<Segment>> edgesMap) {
        super(edgesMap);
    }
}