package tramways.application;

import tramways.application.devices.input.DummyInputDevice;
import tramways.application.devices.output.CliOutputDevice;
import tramways.application.devices.output.OutputDevice;
import tramways.graph.alghoritms.BFS;
import tramways.graph.engine.components.Graph;
import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IGraph;
import tramways.graph.interfaces.INode;

import java.util.ArrayList;

public class ApplicationController {
    DummyInputDevice in = new DummyInputDevice();
    CliOutputDevice out = new CliOutputDevice();

    public void run() throws NodeNotFound {
        TestGraph graph = new TestGraph(in.getCityMap());

        //while(true) {
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
                //break;
            }
        //}
    }

    public void getAllConnections(IGraph graph) throws NodeNotFound {
        BFS getAllRoutes = new BFS<Node, Segment>(graph);

        Node startNode = in.getNode("a");
        Node endNode = in.getNode("d");

        ArrayList<ArrayList<Node>> routes = getAllRoutes.compute(startNode, endNode);
        for(ArrayList<Node> route: routes){
            out.showRoute(route);
        }
    }

    public void getShortestRoute(IGraph graph) {

    }
    public void getCheapestRoute(IGraph graph) {

    }
    public void getFastestRoute(IGraph graph) {

    }
}