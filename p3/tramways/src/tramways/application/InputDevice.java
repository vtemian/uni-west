package tramways.application;

import tramways.graph.engine.CostEdge;
import tramways.graph.interfaces.IEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class InputDevice {
    private Map<Node, ArrayList<Segment>> cityMap = new HashMap<Node, ArrayList<Segment>>();

    public InputDevice() {
        Station balcescu_7 = new Station("7 balcesvu");
        Station balcescu_8= new Station("8 balcesvu");

        List<Station> balcescu = new ArrayList<Station>();
        balcescu.add(balcescu_7);
        balcescu.add(balcescu_8);

        Node a = new Node("a", new Float(45.0), new Float(45.0), balcescu);
        Node b = new Node("b", new Float(45.0), new Float(45.0), balcescu);
        Node c = new Node("c", new Float(45.0), new Float(45.0), balcescu);

        Segment first = new Segment(a, b, new Float(5.0), 70, 50);
        Segment second = new Segment(a, c, new Float(5.0), 70, 50);
        List<Segment> segments = new ArrayList<Segment>();
        segments.add(first);
        segments.add(second);
        this.cityMap.put(a, (ArrayList<Segment>) segments);
    }

    public Map<Node, ArrayList<Segment>> getCityMap() {
        return cityMap;
    }
}
