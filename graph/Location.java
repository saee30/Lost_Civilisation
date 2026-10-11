package graph;

public class Location {

    private int locationId;
    private String name;
    private String description;
    private boolean visited;
    private boolean excavated;
    private boolean unlocked;

    public Location(int locationId, String name, String description) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.visited = false;
        this.excavated = false;
        this.unlocked = true;
    }

    //getter methods
    public int getLocationID() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean isExcavated() {
        return excavated;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    //setter methods
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setExcavated(boolean excavated) {
        this.excavated = excavated;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}
