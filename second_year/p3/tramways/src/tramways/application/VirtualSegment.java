package tramways.application;

import tramways.graph.engine.components.CostEdge;

import java.util.List;

public class VirtualSegment<N extends Node, S extends Station> extends CostEdge<N> implements Comparable<VirtualSegment> {
    private Integer length;
    private Float time;

    public VirtualSegment(N leftNode, N rightNode, List<Segment<S>> segments) {
        super(leftNode, rightNode, new Float(0.0));

        cost = calculateCheapestCost(segments);
        length = calculateShortestCost(segments);
        time = calculateFastestCost(segments);
    }

    public Float getTime(){
        return time;
    }

    public Integer getLength(){
        return length;
    }

    private Float calculateFastestCost(List<Segment<S>> segments){
        Float min_cost = segments.get(0).getTime();

        for(Segment<S> segment: segments){
            if(min_cost > segment.getCost())
                min_cost = segment.getTime();
        }
        return min_cost;
    }

    private Integer calculateShortestCost(List<Segment<S>> segments){
        Integer min_cost = segments.get(0).getLength();

        for(Segment<S> segment: segments){
            if(min_cost > segment.getCost())
                min_cost = segment.getLength();
        }
        return min_cost;
    }

    private Float calculateCheapestCost(List<Segment<S>> segments){
        Float min_cost = segments.get(0).getCost();

        for(Segment<S> segment: segments){
            if(min_cost > segment.getCost())
                min_cost = segment.getCost();
        }
        return min_cost;
    }

    @Override
    public int compareTo(VirtualSegment virtualSegment) {
        return 0;
    }
}
