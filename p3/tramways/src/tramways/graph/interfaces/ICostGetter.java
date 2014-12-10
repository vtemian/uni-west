package tramways.graph.interfaces;

import tramways.application.Segment;
import tramways.application.utils.DistanceGetter;

public interface ICostGetter<Edge extends ICostEdge, Cost extends Number> {
    public Cost getCost(Edge edge);
    public Cost getMAXIMUM();
    public Cost getDEFAULT();
    public Cost addCost(Cost a, Cost b);
    public boolean compare(Cost a, Cost b);
}
