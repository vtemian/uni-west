package tramways.application;

import tramways.graph.exceptions.NodeNotFound;

public class Main {

    public static void main(String[] args) throws NodeNotFound {
        ApplicationController app = new ApplicationController();
        app.run();
    }
}