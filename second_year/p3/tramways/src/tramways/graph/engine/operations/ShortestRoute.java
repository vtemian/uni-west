package tramways.graph.engine.operations;

import tramways.graph.alghoritms.RouteGraphAlgorithm;
import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IGraph;
import tramways.graph.interfaces.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShortestRoute<Strategy extends RouteGraphAlgorithm, Node extends INode> extends Operation<Strategy, Node>{
    private Strategy strategy;
    private IGraph graph;
    private Map<Node, ArrayList<Node>> routesMap = new HashMap<Node, ArrayList<Node>>();
    private ArrayList<ArrayList<Node>> routes = new ArrayList<ArrayList<Node>>();


    public ShortestRoute(Strategy strategy, IGraph graph) {
        super(strategy, graph);
        this.strategy = strategy;
        this.graph = graph;
    }

    public ArrayList<ArrayList<Node>> computeRoute(Node startNode, Node endNode) throws NodeNotFound {
        strategy.setGraph(graph);
        routesMap = (Map<Node, ArrayList<Node>>) strategy.compute(startNode, endNode);

        ArrayList<Node> currentRoute = new ArrayList<Node>();

        composeRoutes(endNode, currentRoute, startNode);

        return routes;
    }

    private void composeRoutes(Node visiting, ArrayList<Node> currentRoute, Node startNode){
        if(visiting.equals(startNode)){
            currentRoute.add(visiting);
            routes.add((ArrayList<Node>) currentRoute.clone());
        }else if(!currentRoute.contains(visiting)){
            currentRoute.add(visiting);
            for(Node node: routesMap.get(visiting)){
                composeRoutes(node, (ArrayList<Node>) currentRoute.clone(), startNode);
                currentRoute.remove(node);
            }
        }
    }
}
