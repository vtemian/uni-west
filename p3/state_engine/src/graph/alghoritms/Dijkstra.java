package graph.alghoritms;

import graph.exceptions.NodeNotFound;
import graph.exceptions.NullNodeException;
import graph.interfaces.ICostEdge;
import graph.interfaces.ICostGetter;
import graph.interfaces.IGraph;
import graph.interfaces.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Dijkstra<Node extends INode, Edge extends ICostEdge<Node>, CostType extends Number> implements RouteGraphAlgorithm<Node, Edge, CostType> {
    private ICostGetter costGetter;
    protected IGraph graph;

    public IGraph getGraph() {
        return graph;
    }

    public void setGraph(IGraph graph) {
        this.graph = graph;
    }

    private class Cost<Node, Cost> {
        public final Node node;
        public final Cost cost;

        public Cost(Node node, Cost cost) {
            this.node = node;
            this.cost = cost;
        }

        public String toString(){
            return node.toString() + " " + cost.toString();
        }
    }

    public Dijkstra(ICostGetter costGetter) {
        this.costGetter = costGetter;
    }

    public Map<Node, ArrayList<Node>> compute(Node startNode, Node endNode) throws NodeNotFound {
        ArrayList<Edge> neighbors = new ArrayList<Edge>();
        ArrayList<Edge> visited = new ArrayList<Edge>();
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();

        Map<Node, Cost<Node, CostType>> costingMap = new HashMap<Node, Cost<Node, CostType>>();
        Map<Node, ArrayList<Node>> routes= new HashMap<Node, ArrayList<Node>>();

        costingMap.put(startNode, new Cost<Node, CostType>(startNode, (CostType)costGetter.getDEFAULT()));

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
            Edge toVisit = queue.poll();
            if(visited.contains(toVisit)) continue;

            Node node = toVisit.getRightNode();

            CostType cost = (CostType) costGetter.getCost(toVisit);
            CostType distance = (CostType) costGetter.getMAXIMUM();

            if(costingMap.containsKey(toVisit.getLeftNode())) {
                distance = costingMap.get(toVisit.getLeftNode()).cost;
            }
            distance = (CostType)costGetter.addCost(cost, distance);

            if(!costingMap.containsKey(node))
                costingMap.put(node, new Cost<Node, CostType>(node, (CostType)costGetter.getMAXIMUM()));

            if(costGetter.compare(costingMap.get(node).cost, distance))
                costingMap.put(node, new Cost<Node, CostType>(toVisit.getLeftNode(), distance));

            try {
                neighbors = graph.getNeighborsEdge(node);
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

        for(Entry<Node, Cost<Node, CostType>> entry: costingMap.entrySet()){
            Cost<Node, CostType> cost = entry.getValue();
            ArrayList<Node> from = new ArrayList<Node>();
            from.add(cost.node);
            routes.put(entry.getKey(), from);
        }

        return routes;
    }
}
