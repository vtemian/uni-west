package tramways.application.devices.input;

import tramways.application.Junction;
import tramways.application.Segment;
import tramways.application.Station;
import tramways.application.VirtualSegment;

import java.util.ArrayList;
import java.util.Map;

public interface IInputDevice {
    public String getOption();
    public Map<Junction, ArrayList<VirtualSegment<Junction, Station>>> getCityMap();
    public Junction getNode(String message);
}