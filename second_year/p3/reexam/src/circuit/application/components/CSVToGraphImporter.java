package circuit.application.components;

import circuit.application.operations.ValueOperation;
import circuit.application.operations.bool.XorOperation;
import circuit.application.operations.interfaces.Operation;
import circuit.importer.csv.CSVImporter;
import graph.engine.components.Edge;
import graph.engine.components.SimpleNode;
import graph.engine.components.WritableDependencyGraph;
import sun.security.util.ManifestDigester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVToGraphImporter extends CSVImporter {
    private WritableDependencyGraph<OperationNode<?>, Edge<OperationNode<?>>> graph;

    public WritableDependencyGraph<OperationNode<?>, Edge<OperationNode<?>>> getGraph() {
        return graph;
    }

    public CSVToGraphImporter(String fileName) {
        super(fileName);
    }

    @Override
    public void importOperations() {
        HashMap<OperationNode<?>, ArrayList<Edge<OperationNode<?>>>> edgeMap = new HashMap<OperationNode<?>, ArrayList<Edge<OperationNode<?>>>>();

        for (String[] info: data){
            OperationNode<?> node_A = getNode(info[0]);
            OperationNode<?> node_B = getNode(info[1]);

            Edge<OperationNode<?>> edge = new Edge<OperationNode<?>>(node_B, node_A);
            ArrayList<Edge<OperationNode<?>>> edges;

            if(edgeMap.containsKey(node_A)) {
                edges = edgeMap.get(node_A);
            } else {
                edges = new ArrayList<Edge<OperationNode<?>>>();
            }

            edges.add(edge);
            edgeMap.put(node_A, edges);
        }
        graph = new WritableDependencyGraph<OperationNode<?>, Edge<OperationNode<?>>>(edgeMap);
    }

    public ArrayList<ArrayList<OperationNode<?>>> getBranches(){
        return graph.getBranches();
    }

    private OperationNode<?> getNode(String info) {
        if(info.toUpperCase().contains("XOR")) {
            XorOperation operation = new XorOperation();
            OperationNode<XorOperation> node = new OperationNode<XorOperation>(info, operation);
            return node;
        }else{
            ValueOperation operation = new ValueOperation(info);
            OperationNode<ValueOperation> node = new OperationNode<ValueOperation>(info, operation);
            node.setReady();
            return node;
        }
    }
}
