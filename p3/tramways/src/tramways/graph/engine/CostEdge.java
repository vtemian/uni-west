package tramways.graph.engine;

import tramways.graph.interfaces.IWritableCostEdge;

import java.util.ArrayList;
import java.util.List;

public class CostEdge<Node> implements IWritableCostEdge<Node> {
    private Node leftNode;
    private Node rightNode;
    private Float cost;

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
    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public void setNodes(Node left, Node rigth) {
        leftNode = left;
        rightNode = rigth;
    }

    @Override
    public Node getOtherNode(Node node) {
        if(node == rightNode) return leftNode;
        return rightNode;
    }
}
