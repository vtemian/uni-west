package tramways.application;

import tramways.graph.WritableCostEdge;

import java.util.List;

public class Node implements WritableCostEdge<Segment>{
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

    @Override
    public void setCost() {

    }

    @Override
    public Float getCost() {
        return cost;
    }

    @Override
    public void setNodes(Segment left, Segment rigth) {

    }

    @Override
    public List<Segment> getNodes() {
        return nodes;
    }
}
