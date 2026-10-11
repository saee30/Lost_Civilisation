package models.queue;

public class ExcavationNode {
    String locationId;
    String clue;
    ExcavationNode next;

    public String getLocationId()
    {
        return locationId;
    }

    public String getClue()
    {
        return clue;
    }

    public ExcavationNode(String locationId, String clue)
    {
        this.locationId = locationId; //temp for now, will link with shared location class
        this.clue = clue;
        this.next = null;
    }
}

