package graph.engine.components;

import graph.interfaces.IEdge;

import java.util.ArrayList;
import java.util.List;

public class Edge<Node> implements IEdge<Node>{
    protected Node leftNode;
    protected Node rightNode;

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "leftNode=" + leftNode.toString() +
                ", rightNode=" + rightNode.toString() +
                '}';
    }

    public Edge(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
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
        if(node.equals(rightNode)) return leftNode;
        return rightNode;
    }

}
