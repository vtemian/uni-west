package tramways.application;

public class Station extends Node{
    private String name;

    public int compareTo(Junction junction) {
        return 0;
    }

    @Override
    public int compareTo(Node node) {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station(String name) {
        this.name = name;
    }
}
