package tramways.application;

import orm.components.ORM;
import orm.connection.JDBCConnection;
import tramways.application.devices.input.CliInputDevice;
import tramways.application.devices.input.DummyInputDevice;
import tramways.application.devices.output.CliOutputDevice;
import tramways.application.importer.Parser;
import tramways.application.importer.models.Line;
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
    //CliInputDevice in = new CliInputDevice();
    DummyInputDevice in = new DummyInputDevice();
    CliOutputDevice out = new CliOutputDevice();

    public void run() throws NodeNotFound {
        TestGraph graph = new TestGraph(in.getCityMap());

        while(true) {
            String option = in.getOption();

            if (option.equals("import")) {
                importData();
            }
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
            break;
        }
    }

    public void getAllConnections(IGraph graph) throws NodeNotFound {
        /*BFS<Junction, Segment, Integer> strategy = new BFS<Junction, Segment, Integer>();
        AllRoutes<BFS, Junction> getAllRoutes = new AllRoutes<BFS, Junction>(strategy, graph);

        Junction startJunction = in.getNode("Enter start node name: ");
        Junction endJunction = in.getNode("Enter end node name");

        ArrayList<ArrayList<Junction>> routes = getAllRoutes.computeRoute(startJunction, endJunction);
        for(ArrayList<Junction> route: routes){
            out.showRoute(route);
        }
        */
    }

    public void getRoute(IGraph graph, RouteGraphAlgorithm strategy) throws NodeNotFound {
        ShortestRoute<Dijkstra, Junction> getAllRoutes = new ShortestRoute<Dijkstra, Junction>((Dijkstra) strategy, graph);

        Junction startJunction = in.getNode("Gara");
        Junction endJunction = in.getNode("AutoGara");

        ArrayList<ArrayList<Junction>> routes = getAllRoutes.computeRoute(startJunction, endJunction);
        for(ArrayList<Junction> route: routes){
           out.showRoute(route);
        }
    }

    public void getCheapestRoute(IGraph graph) throws NodeNotFound {
        CostGetter<VirtualSegment<Junction, Station>, Float> costGetter = new CostGetter<VirtualSegment<Junction, Station>, Float>();
        Dijkstra<Junction, VirtualSegment<Junction, Station>, Float> strategy = new Dijkstra<Junction, VirtualSegment<Junction, Station>, Float>(costGetter);

        getRoute(graph, strategy);
    }

    public void getShortestRoute(IGraph graph) throws NodeNotFound {
        /*
        DistanceGetter<Segment, Integer> distanceGetter = new DistanceGetter();
        Dijkstra<Junction, Segment, Integer> strategy = new Dijkstra<Junction, Segment, Integer>(distanceGetter);

        getRoute(graph, strategy);
        */
    }

    public void getFastestRoute(IGraph graph) throws NodeNotFound {
        /*
        TimeGetter<Segment, Float> distanceGetter = new TimeGetter<Segment, Float>();
        Dijkstra<Junction, Segment, Float> strategy = new Dijkstra<Junction, Segment, Float>(distanceGetter);

        getRoute(graph, strategy);
        */
    }

    public void importData(){
        String dbPass = in.getDBPass();
        String dbUser = in.getDBUser();
        String dbConnectionString = in.getDBConnectionString();

        JDBCConnection connection = new JDBCConnection(dbPass, dbUser, dbConnectionString);
        ORM orm = new ORM("tramways.application.importer.models", connection);
        orm.sync();

        Parser parser = new Parser(in.getCSVFile(), ",");
        for(String[] row: parser.parse()) {
            Line line = new Line(row);
            orm.create(line);
        }
    }
}