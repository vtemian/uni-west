package graph.alghoritms;

import graph.exceptions.NodeNotFound;
import graph.exceptions.NullNodeException;
import graph.interfaces.ICostEdge;
import graph.interfaces.IGraph;
import graph.interfaces.INode;

import java.util.*;

public class BFS<Node extends INode, Edge extends ICostEdge<Node>, CostType extends Number> implements RouteGraphAlgorithm<Node, Edge, CostType> {
    protected IGraph graph;
    public Map<Node, ArrayList<Node>> routesMap = new HashMap<Node, ArrayList<Node>>();

    public IGraph getGraph() {
        return graph;
    }

    public void setGraph(IGraph graph) {
        this.graph = graph;
    }

    public Map<Node, ArrayList<Node>> compute(Node startNode, Node endNode) throws NodeNotFound {
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

        for(Edge neighbor: neighbors){
            queue.add(neighbor);
        }

        while(!queue.isEmpty()) {
            Edge toVisit = queue.remove();
            if(visited.contains(toVisit)) continue;

            ArrayList<Node> nearby = new ArrayList<Node>();
            if(toVisit.getRightNode() != startNode && routesMap.containsKey(toVisit.getRightNode())) {
                nearby = routesMap.get(toVisit.getRightNode());
            }

            nearby.add(toVisit.getLeftNode());
            routesMap.put(toVisit.getRightNode(), nearby);

            try {
                neighbors = graph.getNeighborsEdge(toVisit.getRightNode());
            }catch (NodeNotFound notFound) {
                continue;
            } catch (NullNodeException e) {
                e.printStackTrace();
            }

            for(Edge neighbor: neighbors){
                queue.add(neighbor);
            }

            visited.add(toVisit);
        }
        return routesMap;
    }
}
