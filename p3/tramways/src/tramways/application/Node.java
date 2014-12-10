package tramways.application;

import tramways.graph.interfaces.INode;

import java.util.Comparator;
import java.util.List;

public class Node implements INode, Comparable<Node>{
    private String name;

    @Override
    public int compareTo(Node node) {
        return node.getName().compareTo(name);
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (!latitude.equals(node.latitude)) return false;
        if (!longitude.equals(node.longitude)) return false;
        if (!name.equals(node.name)) return false;
        if (!stations.equals(node.stations)) return false;

        return true;
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
