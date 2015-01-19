package tramways.graph.engine.components;

import tramways.graph.interfaces.INode;

public class SimpleNode implements INode{
    public String name;

    @Override
    public String getName() {
        return name;
    }
}
