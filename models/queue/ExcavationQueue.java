package models.queue;

public class ExcavationQueue {
    private ExcavationNode front;
    private ExcavationNode rear;
    private int size;

    public ExcavationQueue()
    {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(int locationId, String clue)
    {
        ExcavationNode node = new ExcavationNode(locationId, clue);
        if(rear==null)
        {
            front = node;
            rear = node;
        }
        else
        {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public boolean isEmpty()
    {
        return size ==0;
    }

    public int size()
    {
        return size;
    }

    public  ExcavationNode dequeue()
    {
        if(isEmpty()) return null;

        ExcavationNode f = front;
        front = front.next;
        if(front==null) rear=null;
        size--;
        f.next=null;
        return f;

    }
}
