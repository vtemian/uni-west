package graph.engine.components;

import graph.exceptions.NodeNotFound;
import graph.exceptions.NullNodeException;
import graph.interfaces.IWritebleGraph;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WritableDependencyGraph <Node extends SimpleNode, edge extends Edge<Node>> extends Graph<Node, edge> implements IWritebleGraph<Node, edge> {
    public WritableDependencyGraph(Map<Node, ArrayList<edge>> edgesMap) {
        super(edgesMap);
    }

    public ArrayList<ArrayList<Node>> getBranches(){
        ArrayList<ArrayList<Node>> branches = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> startNodes = getStartNodes();
        ArrayList<Node> done = new ArrayList<Node>();

        Queue<ArrayList<Node>> queue = new LinkedList<ArrayList<Node>>();

        for(Node node: startNodes) {
            try {
                for (Node e : getNeighbors(node)) {
                    ArrayList<Node> emptyList = new ArrayList<Node>();
                    emptyList.add(e);
                    branches.add(emptyList);

                    for (Node child : getNeighbors(e)) {
                        ArrayList<Node> nodes = new ArrayList<Node>();
                        nodes.add(child);
                        nodes.add(e);
                        queue.add(nodes);
                    }
                }

                while (!queue.isEmpty()) {
                    ArrayList<Node> visiting = queue.poll();
                    if (done.contains(visiting.get(0))) continue;

                    for (ArrayList<Node> branch : branches) {
                        boolean stop = false;

                        for (Node father : branch) {
                            if (father.equals(visiting.get(1))) {
                                stop = true;
                                done.add(visiting.get(0));
                                try {
                                    for (Node toAdd : getNeighbors(visiting.get(0))) {
                                        ArrayList<Node> nodes = new ArrayList<Node>();
                                        nodes.add(toAdd);
                                        nodes.add(visiting.get(0));
                                        queue.add(nodes);
                                    }
                                    break;
                                } catch (NodeNotFound nodeNotFound) {
                                    break;
                                }
                            }
                        }
                        if (stop) {
                            branch.add(visiting.get(0));
                            break;
                        }
                    }
                }

            } catch (NodeNotFound nodeNotFound) {
                nodeNotFound.printStackTrace();
            } catch (NullNodeException e) {
                e.printStackTrace();
            }
        }
        return branches;
    }

    private ArrayList<Node> getStartNodes(){
        ArrayList<Node> startNodes = new ArrayList<Node>();
        for(Map.Entry<Node, ArrayList<edge>> entry: edgesMap.entrySet()) {
            boolean not_start = true;
            for(Map.Entry<Node, ArrayList<edge>> secondEntry: edgesMap.entrySet()){
                for(edge e: secondEntry.getValue()) {
                    if(e.leftNode.equals(entry.getKey())) {
                        not_start = false;
                    }
                }
            }
            if (not_start) {
                startNodes.add(entry.getKey());
            }
        }
        return startNodes;
    }

    @Override
    public void addNewEdge(edge edge) {

    }

    @Override
    public void addNewNode(Node node) {

    }

    @Override
    public void removeNode(Node node) {

    }

    @Override
    public void removeEdge(edge node) {

    }
}
