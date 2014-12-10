package tramways.application;

import tramways.application.devices.input.CliInputDevice;
import tramways.application.devices.input.DummyInputDevice;
import tramways.application.devices.output.CliOutputDevice;
import tramways.application.utils.CostGetter;
import tramways.application.utils.DistanceGetter;
import tramways.application.utils.TimeGetter;
import tramways.graph.alghoritms.BFS;
import tramways.graph.alghoritms.Dijkstra;
import tramways.graph.alghoritms.RouteGraphAlgorithm;
import tramways.graph.engine.operations.AllRoutes;
import tramways.graph.engine.operations.ShortestRoute;
import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IGraph;

import java.util.ArrayList;

public class ApplicationController {
    CliInputDevice in = new CliInputDevice();
    CliOutputDevice out = new CliOutputDevice();

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
        BFS<Node, Segment, Integer> strategy = new BFS<Node, Segment, Integer>();
        AllRoutes<BFS, Node> getAllRoutes = new AllRoutes<BFS, Node>(strategy, graph);

        Node startNode = in.getNode("Enter start node name: ");
        Node endNode = in.getNode("Enter end node name");

        ArrayList<ArrayList<Node>> routes = getAllRoutes.computeRoute(startNode, endNode);
        for(ArrayList<Node> route: routes){
            out.showRoute(route);
        }
    }

    public void getRoute(IGraph graph, RouteGraphAlgorithm strategy) throws NodeNotFound {
        ShortestRoute<Dijkstra, Node> getAllRoutes = new ShortestRoute<Dijkstra, Node>((Dijkstra) strategy, graph);

        Node startNode = in.getNode("Enter start node name: ");
        Node endNode = in.getNode("Enter end node name: ");

        ArrayList<ArrayList<Node>> routes = getAllRoutes.computeRoute(startNode, endNode);
        for(ArrayList<Node> route: routes){
           out.showRoute(route);
        }
    }

    public void getCheapestRoute(IGraph graph) throws NodeNotFound {
        CostGetter<Segment, Float> costGetter = new CostGetter<Segment, Float>();
        Dijkstra<Node, Segment, Float> strategy = new Dijkstra<Node, Segment, Float>(costGetter);

        getRoute(graph, strategy);
    }

    public void getShortestRoute(IGraph graph) throws NodeNotFound {
        DistanceGetter<Segment, Integer> distanceGetter = new DistanceGetter();
        Dijkstra<Node, Segment, Integer> strategy = new Dijkstra<Node, Segment, Integer>(distanceGetter);

        getRoute(graph, strategy);
    }

    public void getFastestRoute(IGraph graph) throws NodeNotFound {
        TimeGetter<Segment, Float> distanceGetter = new TimeGetter<Segment, Float>();
        Dijkstra<Node, Segment, Float> strategy = new Dijkstra<Node, Segment, Float>(distanceGetter);

        getRoute(graph, strategy);
    }
}