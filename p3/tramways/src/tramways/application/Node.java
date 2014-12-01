package tramways.application;

import java.util.List;

public class Node {
    private String name;
    private Float longitude;
    private Float latitude;
    private List<Station> stations;

    public Node(String name, Float longitude, Float latitude, List<Station> stations) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public Float getLogitude() {
        return longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public List<Station> getStations() {
        return stations;
    }
}
