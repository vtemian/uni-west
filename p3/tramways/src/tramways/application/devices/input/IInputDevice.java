package tramways.application.devices.input;

import tramways.application.Node;
import tramways.application.Segment;

import java.util.ArrayList;
import java.util.Map;

public interface IInputDevice {
    public String getOption();
    public Map<Node, ArrayList<Segment>> getCityMap();
    public Node getNode(String message);
}