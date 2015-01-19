package tramways.application.devices.output;

import java.util.ArrayList;
import tramways.application.Junction;

public interface OutputDevice {
    public void showRoute(ArrayList<Junction> route);
}
