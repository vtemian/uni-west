package graph.engine.components;

import graph.interfaces.INode;
import graph.interfaces.IWritableCostEdge;

public class WritableCostEdge<Node extends INode> extends CostEdge<Node> implements IWritableCostEdge<Node> {
    public WritableCostEdge(Node leftNode, Node rightNode, Float cost) {
        super(leftNode, rightNode, cost);
    }

    @Override
    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public void setNodes(Node left, Node rigth) {
        leftNode = left;
        rightNode = rigth;
    }

}
