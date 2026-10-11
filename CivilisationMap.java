package graph;

public class CivilisationMap {

    private static final int max_locs = 10;
    
    private Location[] locations = new Location[max_locs];
    private int locationCount = 0;

    private GraphNode[] adjacencyList = new GraphNode[max_locs]; //10 vertices (connected to multiple other locations), 10 spots in the adjacency list

    public boolean addLocation(Location loc) {

        if (loc == null) {

            return false;
        }

        int id = loc.getLocationID();

        if(id < 1 || id > max_locs) { //checking if the ID falls within the valid range

            return false;
        }

        int index = id - 1;

        if (locations[index] != null) { //checking if the location already exists

            System.out.println("Location ID already exists.");
            return false;
        }

        //adding the location
        locations[index] = loc;
        locationCount++;

        return true;
    }

    public boolean addEdge(int id1, int id2) {

        if(id1 < 1 || id1 > max_locs || id2 < 1 || id2 > max_locs) { //checking if the IDs fall within the valid range

            return false;
        }

        if(locations[id1 - 1] == null || locations[id2 - 1] == null) { //checking if the locations already exist

            return false;
        }

        if (id1 == id2) {

            return false; //no self-loops
        }

        //adding id2 to id1's adjacency list
        GraphNode newNode1 = new GraphNode(locations[id2 - 1]);
        newNode1.next = adjacencyList[id1 - 1];
        adjacencyList[id1 - 1] = newNode1;

        //also adding id1 to id2's adjacency list as it is an undirected graph
        GraphNode newNode2 = new GraphNode(locations[id1 - 1]);
        newNode2.next = adjacencyList[id2 - 1];
        adjacencyList[id2 - 1] = newNode2;

        return true;
    }
}