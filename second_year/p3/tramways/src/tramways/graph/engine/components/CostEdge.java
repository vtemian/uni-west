package tramways.graph.engine.components;

import tramways.graph.interfaces.ICostEdge;
import tramways.graph.interfaces.INode;

import java.util.ArrayList;
import java.util.List;

public class CostEdge<Node extends INode> implements ICostEdge<Node> {
    protected Node leftNode;
    protected Node rightNode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostEdge)) {
            return false;
        }

        CostEdge costEdge = (CostEdge) o;

        return cost.equals(costEdge.cost) && leftNode.equals(costEdge.leftNode) && rightNode.equals(costEdge.rightNode);

    }

    @Override
    public int hashCode() {
        int result = leftNode.hashCode();
        result = 31 * result + rightNode.hashCode();
        result = 31 * result + cost.hashCode();
        return result;
    }

    protected Float cost;

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

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
