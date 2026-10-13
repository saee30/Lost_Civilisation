package queue;

public class ExcavationNode {
    int locationId;
    String locName;
    String clue;
    ExcavationNode next;

    public int getLocationId()
    {
        return locationId;
    }

    public String getName() 
    {
        return locName;
    }

    public String getClue()
    {
        return clue;
    }

    public ExcavationNode(int locationId, String locName, String clue)
    {
        this.locationId = locationId; //temp for now, will link with shared location class
        this.locName=locName;
        this.clue = clue;
        this.next = null;
    }
}

