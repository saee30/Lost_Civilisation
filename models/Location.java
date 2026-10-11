package models;
import java.util.ArrayList;
public class Location {
    
    private String id;
    private String name;
    private ArrayList<Artifact> hidden = new ArrayList<>();

    public Location(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id;}
    public String getName(){ return name;}

    public void addArtifact(Artifact a)
    {
        hidden.add(a);
    }

    public ArrayList<Artifact> revealRemaining()
    {
        ArrayList<Artifact> found = new ArrayList<>(hidden);
        hidden.clear();
        return found;
    }
}
