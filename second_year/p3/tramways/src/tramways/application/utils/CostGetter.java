package tramways.application.utils;

import tramways.application.Segment;
import tramways.graph.engine.components.CostEdge;
import tramways.graph.interfaces.ICostGetter;

public class CostGetter<Edge extends CostEdge, Cost extends Number> implements ICostGetter<Edge, Cost> {
    @Override
    public Cost getCost(Edge edge) {
        return (Cost)edge.getCost();
    }

    @Override
    public Cost getMAXIMUM() {
        return (Cost)new Float(Float.MAX_VALUE);
    }

    @Override
    public Cost getDEFAULT() {
        return (Cost)new Float(0.);
    }

    @Override
    public Cost addCost(Cost a, Cost b) {
        return (Cost)new Float((Float)a + (Float)b);
    }

    @Override
    public boolean compare(Cost a, Cost b) {
        return (Float)a > (Float)b;
    }
}
