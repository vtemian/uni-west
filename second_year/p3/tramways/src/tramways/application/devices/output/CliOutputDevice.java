package tramways.application.devices.output;

import tramways.application.Junction;

import java.util.ArrayList;

public class CliOutputDevice implements OutputDevice{

    @Override
    public void showRoute(ArrayList<Junction> route) {
        for(Junction node: route) {
            System.out.println(node.getName());
        }
    }
}
