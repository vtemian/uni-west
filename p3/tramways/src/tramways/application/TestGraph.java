package tramways.application;

import tramways.graph.engine.components.CostEdge;
import tramways.graph.engine.components.Graph;
import tramways.graph.engine.components.SimpleNode;

import java.util.ArrayList;
import java.util.Map;

public class TestGraph<Junction extends SimpleNode, VirtualSegment extends CostEdge<Junction>> extends Graph<Junction, VirtualSegment>{
    public TestGraph(Map<Junction, ArrayList<VirtualSegment>> edgesMap) {
        super(edgesMap);
    }
}