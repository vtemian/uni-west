package tramways.application;

import tramways.application.devices.input.DummyInputDevice;
import tramways.application.devices.output.OutputDevice;
import tramways.graph.alghoritms.BFS;
import tramways.graph.engine.components.Graph;
import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IGraph;
import tramways.graph.interfaces.INode;

import java.util.ArrayList;

public class ApplicationController {
    DummyInputDevice in = new DummyInputDevice();
    OutputDevice out = new OutputDevice();

    public void run() throws NodeNotFound {
        TestGraph graph = new TestGraph(in.getCityMap());

        while(true) {
            String option = in.getOption();

            if (option.equals("fastest")) {
                getFastestRoute(graph);
            }
            if (option.equals("shortest")){
                getShortestRoute(graph);
            }
            if (option.equals("cheapest")){
                getCheapestRoute(graph);
            }
            if (option.equals("all")){
                getAllConnections(graph);
            }
            if (option.equals("exit")){
                break;
            }
        }
    }

    public void getAllConnections(IGraph graph) throws NodeNotFound {
        BFS algo = new BFS<Node>(graph);
        Node startNode = in.generateNode("a");
        Node endNode = in.generateNode("e");
        ArrayList<ArrayList<INode>> routes = algo.compute(startNode, endNode);
        for(ArrayList<INode> route: routes){
            for(INode node: route){
                System.out.println(node.getName());
            }
        }
    }

    public void getShortestRoute(IGraph graph) {

    }
    public void getCheapestRoute(IGraph graph) {

    }
    public void getFastestRoute(IGraph graph) {

    }
}