package tramways.application.devices.input;

import tramways.application.Junction;
import tramways.application.Segment;
import tramways.application.Station;
import tramways.application.VirtualSegment;

import java.util.ArrayList;
import java.util.List;

public class DummyInputDevice extends CliInputDevice{
    public DummyInputDevice() {
        generateCityMap();
    }

    public String getOption(){
        return "parse";
    }

    @Override
    public Junction getNode(String name) {
        for(Junction junction : cityMap.keySet()) {
            if(junction.getName().equals(name)) {
                return junction;
            }
        }
        return null;
    }

    public void generateCityMap(){
        Station tv1_gara = new Station("tv1_gara");
        Station tv2_gara = new Station("tv2_gara");
        Station walk = new Station("walk");

        List<Station> gara_stations = new ArrayList<Station>();
        gara_stations.add(tv1_gara);
        gara_stations.add(tv2_gara);
        gara_stations.add(walk);


        Station tv1_autogara = new Station("tv1_autogara");
        Station tv2_autogara = new Station("tv2_autogara");
        Station walk_autogara = new Station("walk_autogara");

        List<Station> auto_gara_stations = new ArrayList<Station>();
        auto_gara_stations.add(tv1_autogara);
        auto_gara_stations.add(tv2_autogara);
        auto_gara_stations.add(walk_autogara);

        Station tv1_700 = new Station("tv1_700");
        Station tv2_700 = new Station("tv2_700");
        Station walk_700 = new Station("walk_700");

        List<Station> sapte_stations = new ArrayList<Station>();
        sapte_stations.add(tv1_700);
        sapte_stations.add(tv2_700);
        sapte_stations.add(walk_700);

        Junction gara = new Junction("Gara", new Float(57.0), new Float(56.1), gara_stations);
        Junction auto_gara = new Junction("AutoGara", new Float(57.1), new Float(56.2), auto_gara_stations);
        Junction sapte = new Junction("700", new Float(57.1), new Float(56.2), sapte_stations);

        Segment<Station> first = new Segment<Station>(tv1_autogara, tv1_gara, new Float(0.2), 70, 50);
        Segment<Station> fourth = new Segment<Station>(tv2_autogara, tv2_gara, new Float(0.2), 70, 50);
        Segment<Station> fifth = new Segment<Station>(walk, walk_autogara, new Float(0.2), 70, 50);
        List<Segment<Station>> segments = new ArrayList<Segment<Station>>();
        segments.add(first);
        segments.add(fourth);
        segments.add(fifth);

        VirtualSegment<Junction, Station> gara_to_auto_gara = new VirtualSegment<Junction, Station>(gara, auto_gara, segments);

        Segment<Station> secodn = new Segment<Station>(tv1_gara, tv1_autogara, new Float(0.2), 70, 50);
        Segment<Station> third = new Segment<Station>(tv2_gara, tv2_autogara, new Float(0.2), 70, 50);
        Segment<Station> sixth = new Segment<Station>(walk_autogara, walk, new Float(0.2), 70, 50);
        List<Segment<Station>> segments1 = new ArrayList<Segment<Station>>();
        segments1.add(secodn);
        segments1.add(third);
        segments1.add(sixth);
        VirtualSegment<Junction, Station> auto_gara_to_gara = new VirtualSegment<Junction, Station>(auto_gara, gara, segments1);

        ArrayList<VirtualSegment<Junction, Station>> s = new ArrayList<VirtualSegment<Junction, Station>>();
        ArrayList<VirtualSegment<Junction, Station>> s2 = new ArrayList<VirtualSegment<Junction, Station>>();

        s.add(gara_to_auto_gara);
        s2.add(gara_to_auto_gara);

        cityMap.put(gara, s);
        cityMap.put(auto_gara, s2);

        /*
        Junction b = generateNode("b");
        Junction c = generateNode("c");
        Junction d = generateNode("d");
        Junction e = generateNode("e");

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
        */
    }

    public Junction generateNode(String name){
        Station balcescu_7 = new Station("7 balcesvu");
        Station balcescu_8= new Station("8 balcesvu");

        List<Station> balcescu = new ArrayList<Station>();
        balcescu.add(balcescu_7);
        balcescu.add(balcescu_8);

        return new Junction(name, new Float(45.0), new Float(45.0), balcescu);
    }
}
