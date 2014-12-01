package tramways.graph.engine;

import tramways.graph.interfaces.ICostEdge;
import tramways.graph.interfaces.INode;

import java.util.ArrayList;
import java.util.List;

public class CostEdge<Node extends INode> implements ICostEdge<Node> {
    protected Node leftNode;
    protected Node rightNode;
    protected Float cost;

    public CostEdge(Node leftNode, Node rightNode, Float cost) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.cost = cost;
    }

    @Override
    public Float getCost() {
        return cost;
    }

    @Override
    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(leftNode);
        nodes.add(rightNode);
        return nodes;
    }

    @Override
    public Node getOtherNode(Node node) {
        if(node == rightNode) return leftNode;
        return rightNode;
    }
}
