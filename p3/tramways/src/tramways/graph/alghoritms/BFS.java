package tramways.graph.alghoritms;

import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.interfaces.IGraph;
import tramways.graph.interfaces.INode;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS<Node extends INode> extends RouteGraphAlgorithm {
    public Map<Node, ArrayList<Node>> routes = new HashMap<Node, ArrayList<Node>>();
    private ArrayList<ArrayList<Node>> r = new ArrayList<ArrayList<Node>>();

    public BFS(IGraph graph) {
        super(graph);
    }

    public ArrayList<ArrayList<Node>> compute(Node startNode, Node endNode) throws NodeNotFound {
        ArrayList<Node> visited = new ArrayList<Node>();
        routes.put(startNode, null);

        Queue<Node> queue = new LinkedList<Node>();

        queue.add(startNode);
        visited.add(startNode);

        while(!queue.isEmpty()) {
            Node toVisit = queue.remove();
            visited.add(toVisit);

            ArrayList<Node> neighbors = new ArrayList<Node>();
            try {
                neighbors = graph.getNeighbors(toVisit);
            }catch (NodeNotFound notFound) {
                continue;
            }

            for(Node neighbor: neighbors){
                if(visited.contains(neighbor)) continue;

                queue.add(neighbor);

                ArrayList<Node> nearby = new ArrayList<Node>();
                if(routes.containsKey(neighbor)) {
                    nearby = routes.get(neighbor);
                }

                nearby.add(toVisit);
                routes.put(neighbor, nearby);
            }
        }
        for(Node toVisit: routes.get(endNode)){
            ArrayList<Node> currentRoute = new ArrayList<Node>();
            composeRoutes(toVisit, currentRoute);
        }
        return r;
    }

    private void composeRoutes(Node visiting, ArrayList<Node> currentRoute){
        if(routes.get(visiting) == null) {
            r.add((ArrayList<Node>) currentRoute.clone());
        }else{

            for(Node node: routes.get(visiting)){
                currentRoute.add(node);
                composeRoutes(node, currentRoute);
                currentRoute.remove(node);
            }
        }
    }
}
