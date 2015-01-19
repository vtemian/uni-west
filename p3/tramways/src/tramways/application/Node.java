package tramways.application;

import tramways.graph.engine.components.SimpleNode;
import tramways.graph.interfaces.INode;

public abstract class Node extends SimpleNode implements Comparable<Node>{
    public abstract int compareTo(Node node);

    @Override
    public String getName() {
        return null;
    }

}
