package tramways.application.devices.input;

import tramways.application.Node;
import tramways.application.Segment;
import tramways.application.Station;

import java.util.ArrayList;
import java.util.List;

public class DummyInputDevice extends CliInputDevice{
    public DummyInputDevice() {
        generateCityMap();
    }

    public String getOption(){
        return "cheapest";
    }

    @Override
    public Node getNode(String name) {
        for(Node node: cityMap.keySet()) {
            if(node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public void generateCityMap(){
        Node a = generateNode("a");
        Node b = generateNode("b");
        Node c = generateNode("c");
        Node d = generateNode("d");
        Node e = generateNode("e");

        Segment first = new Segment(a, b, new Float(0.2), 70, 50);
        Segment second = new Segment(a, c, new Float(2.0), 70, 50);
        Segment q = new Segment(c, d, new Float(3.0), 70, 50);
        Segment w = new Segment(d, e, new Float(2.0), 70, 50);
        Segment s = new Segment(b, e, new Float(1.0), 70, 50);
        Segment f = new Segment(b, a, new Float(0.2), 70, 50);
        Segment q1 = new Segment(d, c, new Float(3.0), 70, 50);
        Segment e1 = new Segment(e, d, new Float(2.0), 70, 50);
        Segment e2 = new Segment(e, b, new Float(1.0), 70, 50);


        List<Segment> segments = new ArrayList<Segment>();
        segments.add(first);
        segments.add(second);
        cityMap.put(a, (ArrayList<Segment>) segments);

        List<Segment> segments1 = new ArrayList<Segment>();
        segments1.add(q);
        cityMap.put(c, (ArrayList<Segment>) segments1);

        List<Segment> segments2 = new ArrayList<Segment>();
        segments2.add(s);
        segments2.add(f);
        cityMap.put(b, (ArrayList<Segment>) segments2);

        List<Segment> segments3 = new ArrayList<Segment>();
        segments3.add(w);
        segments3.add(q1);
        cityMap.put(d, (ArrayList<Segment>) segments3);

        List<Segment> segments4 = new ArrayList<Segment>();
        segments4.add(e1);
        segments4.add(e2);
        cityMap.put(e, (ArrayList<Segment>) segments4);
    }

    public Node generateNode(String name){
        Station balcescu_7 = new Station("7 balcesvu");
        Station balcescu_8= new Station("8 balcesvu");

        List<Station> balcescu = new ArrayList<Station>();
        balcescu.add(balcescu_7);
        balcescu.add(balcescu_8);

        return new Node(name, new Float(45.0), new Float(45.0), balcescu);
    }
}
