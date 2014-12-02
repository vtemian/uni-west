package tramways.application.devices.input;

import tramways.application.Node;
import tramways.application.Segment;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CliInputDevice implements IInputDevice{
    protected Map<Node, ArrayList<Segment>> cityMap = new HashMap<Node, ArrayList<Segment>>();

    @Override
    public String getOption() {

        return null;
    }

    @Override
    public Map<Node, ArrayList<Segment>> getCityMap() {
        return cityMap;
    }
}
