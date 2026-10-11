package models.queue;

public class ExcavationNode {
    int locationId;
    String clue;
    ExcavationNode next;

    public int getLocationId()
    {
        return locationId;
    }

    public String getClue()
    {
        return clue;
    }

    public ExcavationNode(int locationId, String clue)
    {
        this.locationId = locationId; //temp for now, will link with shared location class
        this.clue = clue;
        this.next = null;
    }
}

