package queue;

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

    public void enqueue(int locationId, String locName, String clue)
    {
        ExcavationNode node = new ExcavationNode(locationId, locName, clue);
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

    public ExcavationNode dequeue() {
        if (isEmpty()) {
            return null;
        }

        ExcavationNode f = front;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        f.next = null;

        System.out.println("Request dequeued. Queue size: " + size);

        return f;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No pending excavation requests.");
            return;
        }

        ExcavationNode current = front;
        System.out.println("Pending excavation requests:");

        while (current != null) {
            System.out.println("Location ID: " + current.getLocationId()+"- "+current.getName());
            current = current.next;
        }
    }

    public int peekLocationId() {
        if (isEmpty()) {
            return -1;
        }

        return front.getLocationId();
    }
}
