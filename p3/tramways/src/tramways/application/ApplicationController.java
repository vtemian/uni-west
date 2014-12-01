package tramways.application;

import tramways.graph.exceptions.NodeNotFound;

import java.util.List;
import java.util.ArrayList;

public class ApplicationController {
    InputDevice in = new InputDevice();
    OutputDevice out = new OutputDevice();

    public void run(){
        // all the logic goes here
        TestGraph graph = new TestGraph(in.getCityMap());

        Station balcescu_7 = new Station("7 balcesvu");
        Station balcescu_8= new Station("8 balcesvu");

        List<Station> balcescu = new ArrayList<Station>();
        balcescu.add(balcescu_7);
        balcescu.add(balcescu_8);

        Node a = new Node("a", new Float(45.0), new Float(45.0), balcescu);
        try {
            ArrayList<Node> nodes = new ArrayList<Node>();
            nodes = (ArrayList<Node>) graph.getNeighbors(a);
            for(Node node: nodes){
                System.out.println(node.getName());
            }

        } catch (NodeNotFound nodeNotFound) {
            nodeNotFound.printStackTrace();
        }
    }
}