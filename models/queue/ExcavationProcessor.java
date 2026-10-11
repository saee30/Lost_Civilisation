package models.queue;

import models.Artifact;
import models.Location;
import java.util.ArrayList;

public class ExcavationProcessor {
    private ExcavationQueue queue;
    private ArrayList<Location> locations;

    public ExcavationProcessor(ExcavationQueue queue, ArrayList<Location> locations) {
        this.queue = queue;
        this.locations = locations;
    }

    private Location findLocation(String id) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getId().equals(id)) {
                return locations.get(i);
            }
        }
        return null;
    }

    public ArrayList<Artifact> processNext() {
        ExcavationNode request = queue.dequeue();
        if (request == null) {
            System.out.println("No excavation requests waiting.");
            return new ArrayList<>();
        }

        Location loc = findLocation(request.getLocationId());
        if (loc == null) {
            System.out.println("Unknown location: " + request.getLocationId());
            return new ArrayList<>();
        }

        System.out.println("Excavating " + loc.getName() + " (clue: " + request.getClue() + ")");
        return loc.revealRemaining();
    }
}