package tramways.application.utils;

import tramways.application.Segment;
import tramways.graph.interfaces.ICostGetter;

public class DistanceGetter<Edge extends Segment, Cost extends Number> implements ICostGetter<Edge, Cost> {
    @Override
    public Cost getCost(Edge edge) {
        return (Cost)edge.getLength();
    }

    @Override
    public Cost getMAXIMUM() {
        return (Cost)new Integer(Integer.MAX_VALUE);
    }

    @Override
    public Cost getDEFAULT() {
        return (Cost)new Integer(0);
    }

    @Override
    public Cost addCost(Cost a, Cost b) {
        return (Cost)new Integer((Integer)a + (Integer)b);
    }

    @Override
    public boolean compare(Cost a, Cost b) {
        return (Integer)a > (Integer)b;
    }
}
