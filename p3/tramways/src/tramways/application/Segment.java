package tramways.application;

import tramways.graph.engine.components.CostEdge;

public class Segment extends CostEdge<Node> implements Comparable<Segment>{
    private Integer length;
    private int speed;

    public Segment(Node leftNode, Node rightNode, Float cost, Integer length, int speed) {
        super(leftNode, rightNode, cost);
        this.length = length;
        this.speed = speed;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Float getTime() {
        return new Float((60 * length) / speed);
    }

    @Override
    public int compareTo(Segment segment) {
        return new Integer(segment.length).compareTo(new Integer(segment.getLength()));
    }
}
