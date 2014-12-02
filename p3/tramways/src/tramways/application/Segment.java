package tramways.application;

import tramways.graph.engine.components.CostEdge;

public class Segment extends CostEdge<Node>{
    private int length;
    private int speed;

    public Segment(Node leftNode, Node rightNode, Float cost, int length, int speed) {
        super(leftNode, rightNode, cost);
        this.length = length;
        this.speed = speed;
    }
}
