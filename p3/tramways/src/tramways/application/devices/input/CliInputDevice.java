package tramways.application.devices.input;

import tramways.application.Junction;
import tramways.application.Station;
import tramways.application.VirtualSegment;

import java.util.*;

public class CliInputDevice implements IInputDevice{
    protected Map<Junction, ArrayList<VirtualSegment<Junction, Station>>> cityMap = new HashMap<Junction, ArrayList<VirtualSegment<Junction, Station>>>();

    public CliInputDevice(){
        generateCityMap();
    }

    public void generateCityMap() {
        Scanner in = new Scanner(System.in);

        String text =   "" +
                            "Hey there! Nice to see you! Let's generate the city map!\n" +
                            "We can do it the hard way, by entering manually the station, or just generate it.\n\n" +
                            "1) Generate a random map for me\n" +
                            "2) I will enter my own map\n" +
                            "3) Done!\n" +
                            "\tEnter you option:" +
                        "";

        System.out.println(text);
        String input = in.nextLine();

        if(input.equals("1"))
            //generateMap();
            System.out.println("nup");
        else if(input.equals("2"))
            manualMap();
        else if (!input.equals("3")){
            System.out.println("I don't understand you..let's try again\n");
            generateCityMap();
        } else if(cityMap != null){
            System.out.println("Cool, let's go now!\n");
        } else {
            System.out.println("Sorry, we need a map!\n");
            generateCityMap();
        }
    }
    /*
    public void generateMap(){
        Random randomGenerator = new Random();
        Station station;
        ArrayList<Junction> junctions = new ArrayList<Junction>();
        ArrayList<Station> stations = new ArrayList<Station>();

        String[] randomNodeNames = {"AEM", "Judetean", "Cluj", "Michelangelo", "Continental", "Iulius Mall",
                                    "Sagului", "Catedrala", "Politehnica", "Marasti", "Dacia Service"};
        String[] randomStationNames = {"AEM 4", "Piata 700 Tv4", "A.Lapusneanu 1", "Catedrala 1", "Abator",
                                       "Agronomiei", "Aleea Ripensia", "Olimpia 1", "Olimpia 2", "Balcescu",
                                       "Venus", "Veteranilor", "V.Babes", "UniVest", "Hector",
                                       "Balcescu 1", "Balcescu 2", "Balcescu 3", "Veteranilor", "Zalau 1"};

        for(String name: randomStationNames){
            stations.add(new Station(name));
        }

        int nodesNumber = randomGenerator.nextInt(6);
        System.out.println("We've generate " + nodesNumber + " nodes: ");

        for (int idx = 1; idx <= nodesNumber; ++idx) {
            int randomInt = randomGenerator.nextInt(randomNodeNames.length - 1);
            System.out.println(idx + ". " + randomNodeNames[randomInt]);

            int stationsNumber = randomGenerator.nextInt((stations.size() - 1) / 2);
            ArrayList<Station> forNode = new ArrayList<Station>();
            for(int jdx = 1; jdx <= stationsNumber; ++jdx) {
                int randomStation = randomGenerator.nextInt(stations.size() - 1);
                station = stations.get(randomStation);
                forNode.add(station);
                stations.remove(station);
                System.out.println("\t" + jdx + " ." + station.getName());
            }
            junctions.add(new Junction(randomNodeNames[randomInt], randomGenerator.nextFloat(),
                               randomGenerator.nextFloat(), stations));
        }

        int segments = randomGenerator.nextInt((nodesNumber * (nodesNumber + 1)) / 4);
        Junction junction;
        for (int idx = 0; idx <= segments; ++idx) {
            int randomNode = randomGenerator.nextInt(junctions.size() - 1);
            junction = junctions.get(randomNode);

            int randomN = randomGenerator.nextInt(6);
            for (int jdx = 0; jdx <= randomN; ++jdx) {
                if(junctions.get(jdx).equals(junction)) continue;

                float cost = randomGenerator.nextFloat();
                int length = randomGenerator.nextInt();
                int speed = randomGenerator.nextInt();

                System.out.println(randomN);
                Segment to = new Segment(junction, junctions.get(jdx), cost, length, speed);
                Segment from = new Segment(junctions.get(jdx), junction, cost, length, speed);

                ArrayList<Segment> segmentsList = new ArrayList<Segment>();
                segmentsList.add(to);
                if (cityMap.containsKey(junction)) {
                    segmentsList.addAll(cityMap.get(junction));
                }
                cityMap.put(junction, segmentsList);

                ArrayList<Segment> segmentsList1 = new ArrayList<Segment>();
                segmentsList1.add(from);
                if (cityMap.containsKey(junctions.get(jdx))) {
                    segmentsList1.addAll(cityMap.get(junctions.get(jdx)));
                }
                cityMap.put(junctions.get(jdx), segmentsList1);
            }
        }

        System.out.println("Done! Ready to start playing around!");
    }
    */
    public void manualMap() {
        System.out.println("Sorry, but for now this is not implemented!");
        generateCityMap();
    }

    @Override
    public String getOption() {
        Scanner in = new Scanner(System.in);
        String text = "\n\nLet's play\n" +
                    "1. Get all connections\n" +
                    "2. Get fastest route\n" +
                    "3. Get cheapest route\n" +
                    "4. Get shortest route\n" +
                    "5. Exit" +
                "";

        System.out.println(text);
        String input = in.nextLine();
        if (input.equals("1"))
            return "all";
        else if (input.equals("2"))
            return "fastest";
        else if (input.equals("3"))
            return "cheapest";
        else if (input.equals("4"))
            return "shortest";
        else if (input.equals("5"))
            return "exit";
        else{
            System.out.println("I don't get this...please try again");
            return getOption();
        }
    }

    @Override
    public Map<Junction, ArrayList<VirtualSegment<Junction, Station>>> getCityMap() {
        return cityMap;
    }

    @Override
    public Junction getNode(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        String name = in.nextLine();

        for(Junction junction : cityMap.keySet()) {
            System.out.println(junction.getName());
            if(junction.getName().equals(name)) return junction;
        }
        System.out.println("Invalid node. Let's try again");
        return getNode(message);
    }
}
