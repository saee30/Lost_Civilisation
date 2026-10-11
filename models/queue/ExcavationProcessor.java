package models.queue;

import bst.ArtifactBST;
import graph.Location;
import models.Artifact;
import java.util.ArrayList;

public class ExcavationProcessor {
    private ExcavationQueue queue;
    private ArrayList<Location> locations;
    private ArrayList<Artifact> artifacts;   // master list of all artefacts
    private ArtifactBST database;            // searchable artefact database

    public ExcavationProcessor(ExcavationQueue queue, ArrayList<Location> locations,
                               ArrayList<Artifact> artifacts, ArtifactBST database) {
        this.queue = queue;
        this.locations = locations;
        this.artifacts = artifacts;
        this.database = database;
    }

    private Location findLocation(int id) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getLocationID() == id) {
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
        if (!loc.isUnlocked()) {
            System.out.println(loc.getName() + " is locked.");
            return new ArrayList<>();
        }
        if (loc.isExcavated()) {
            System.out.println(loc.getName() + " has already been excavated.");
            return new ArrayList<>();
        }

        System.out.println("Excavating " + loc.getName() + " (clue: " + request.getClue() + ")");
        loc.setExcavated(true);

        ArrayList<Artifact> found = new ArrayList<>();
        for (int i = 0; i < artifacts.size(); i++) {
            if (artifacts.get(i).getLocation().equals(loc.getName())) {
                found.add(artifacts.get(i));
            }
        }

        // add every discovery to the searchable database
        for (int i = 0; i < found.size(); i++) {
            database.insert(found.get(i));
        }
        return found;
    }
}
