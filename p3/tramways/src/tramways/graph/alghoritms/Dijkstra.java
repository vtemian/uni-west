package tramways.graph.alghoritms;

import tramways.graph.engine.components.CostEdge;
import tramways.graph.engine.components.SimpleNode;
import tramways.graph.interfaces.ICostGetter;
import tramways.graph.interfaces.INode;
import tramways.graph.interfaces.IGraph;
import tramways.graph.interfaces.ICostEdge;
import tramways.graph.exceptions.NodeNotFound;
import tramways.graph.exceptions.NullNodeException;

import java.util.*;
import java.util.Map.Entry;

public class Dijkstra<Node extends SimpleNode, Edge extends CostEdge<Node>, CostType extends Number> implements RouteGraphAlgorithm<Node, Edge, CostType> {
    private ICostGetter<Edge, CostType> costGetter;
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

        costingMap.put(startNode, new Cost<Node, CostType>(startNode, costGetter.getDEFAULT()));

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

            CostType cost = costGetter.getCost(toVisit);
            CostType distance = costGetter.getMAXIMUM();

            if(costingMap.containsKey(toVisit.getLeftNode())) {
                distance = costingMap.get(toVisit.getLeftNode()).cost;
            }
            distance = costGetter.addCost(cost, distance);

            if(!costingMap.containsKey(node))
                costingMap.put(node, new Cost<Node, CostType>(node, costGetter.getMAXIMUM()));

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
