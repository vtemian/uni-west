package tramways.application;

import java.util.List;

public class Junction extends Node{
    private String name;

    private Float longitude;
    private Float latitude;
    private List<Station> stations;
    private List<Segment> paths;

    public Junction(String name, Float longitude, Float latitude, List<Station> stations) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.stations = stations;
    }

    @Override
    public int compareTo(Node node) {
        return node.getName().compareTo(name);
    }

    public int compareTo(Junction node) {
        return node.getName().compareTo(name);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Junction)) return false;

        Junction junction = (Junction) o;

        return latitude.equals(junction.latitude) && longitude.equals(junction.longitude) && name.equals(junction.name) && stations.equals(junction.stations);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + longitude.hashCode();
        result = 31 * result + latitude.hashCode();
        result = 31 * result + stations.hashCode();
        return result;
    }
}
