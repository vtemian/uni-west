package circuit.application;

import circuit.application.components.OperationNode;
import circuit.application.devices.input.CliInputDevice;
import circuit.application.devices.output.CliOutputDevice;
import circuit.application.components.CSVToGraphImporter;
import circuit.simulator.Simulator;
import graph.engine.components.Edge;
import graph.engine.components.WritableDependencyGraph;

import java.util.ArrayList;

public class Application {
    private CliInputDevice in;
    private CliOutputDevice out;

    public Application(CliInputDevice in, CliOutputDevice out) {
        this.in = in;
        this.out = out;
    }

    public void run(){
        WritableDependencyGraph<OperationNode<?>, Edge<OperationNode<?>>> graph;
        CSVToGraphImporter importer = new CSVToGraphImporter(in.getCSVFilepath());
        importer.importOperations();

        ArrayList<ArrayList<OperationNode<?>>> branches = importer.getBranches();
        graph = importer.getGraph();

        Simulator simulator = new Simulator(in, out);
        simulator.simulate(branches, graph);
    }

    public CliInputDevice getIn() {
        return in;
    }

    public void setIn(CliInputDevice in) {
        this.in = in;
    }

    public CliOutputDevice getOut() {
        return out;
    }

    public void setOut(CliOutputDevice out) {
        this.out = out;
    }
}
