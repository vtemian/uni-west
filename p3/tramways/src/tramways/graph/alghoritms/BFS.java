package tramways.graph.alghoritms;

import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.exceptions.NullNodeException;
import tramways.graph.interfaces.ICostEdge;
import tramways.graph.interfaces.IGraph;
import tramways.graph.interfaces.INode;

import java.util.*;

public class BFS<Node extends INode, Edge extends ICostEdge<Node>> extends RouteGraphAlgorithm {
    public Map<Node, ArrayList<Node>> routesMap = new HashMap<Node, ArrayList<Node>>();
    private ArrayList<ArrayList<Node>> routes = new ArrayList<ArrayList<Node>>();

    public BFS(IGraph graph) {
        super(graph);
    }

    public ArrayList<ArrayList<Node>> compute(Node startNode, Node endNode) throws NodeNotFound {
        ArrayList<Edge> neighbors = new ArrayList<Edge>();
        ArrayList<Edge> visited = new ArrayList<Edge>();
        routesMap.put(startNode, null);

        Queue<Edge> queue = new LinkedList<Edge>();

        try {
            neighbors = graph.getNeighborsEdge(startNode);
        }catch (NodeNotFound notFound) {
            notFound.printStackTrace();
        } catch (NullNodeException e) {
            e.printStackTrace();
        }

        queue.add(neighbors.get(0));

        while(!queue.isEmpty()) {
            Edge toVisit = queue.remove();

            try {
                neighbors = graph.getNeighborsEdge(toVisit.getLeftNode());
            }catch (NodeNotFound notFound) {
                continue;
            } catch (NullNodeException e) {
                e.printStackTrace();
            }

            for(Edge neighbor: neighbors){
                if(visited.contains(neighbor)) continue;

                queue.add(neighbor);

                ArrayList<Node> nearby = new ArrayList<Node>();
                if(neighbor.getRightNode() != startNode && routesMap.containsKey(neighbor.getRightNode())) {
                    nearby = routesMap.get(neighbor.getRightNode());
                }

                nearby.add(toVisit.getLeftNode());
                routesMap.put(neighbor.getRightNode(), nearby);
            }
            visited.add(toVisit);
        }

        for(Node toVisit: routesMap.get(endNode)){
            ArrayList<Node> currentRoute = new ArrayList<Node>();
            composeRoutes(toVisit, currentRoute);
        }

        return routes;
    }

    private void composeRoutes(Node visiting, ArrayList<Node> currentRoute){
        if(routesMap.get(visiting) == null) {
            routes.add((ArrayList<Node>) currentRoute.clone());
        }else{
            for(Node node: routesMap.get(visiting)){
                currentRoute.add(node);
                composeRoutes(node, currentRoute);
                currentRoute.remove(node);
            }
        }
    }
}
