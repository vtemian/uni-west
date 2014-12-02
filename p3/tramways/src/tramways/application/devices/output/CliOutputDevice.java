package tramways.application.devices.output;

import tramways.application.Node;

import java.util.ArrayList;

public class CliOutputDevice implements OutputDevice{

    @Override
    public void showRoute(ArrayList<Node> route) {
        for(Node node: route) {
            System.out.println(node.getName());
        }
    }
}
