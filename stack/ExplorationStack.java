
public class ExplorationStack // Stack location operations 
{

    private StackNode top; // Top node of the stack

    // Check whether the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Add a location to the stack
    public void push(String location) {
        StackNode newNode = new StackNode(location);

        newNode.next = top;
        top = newNode;

        System.out.println(location + " added to exploration history.");
    }

    // Remove the top location
    public String pop() {
        if (isEmpty()) {
            System.out.println("No previous location available!");
            return null;
        }

        String location = top.location;
        top = top.next;

        return location;
    }

    // View the top location
    public String peek() {
        if (isEmpty()) {
            return null;
        }

        return top.location;
    }

    // Display all locations in the stack
    public void display() {
        if (isEmpty()) {
            System.out.println("Exploration history is empty.");
            return;
        }

        StackNode current = top;

        System.out.println("Exploration History (latest first):");

        while (current != null) {
            System.out.println(current.location);
            current = current.next;
        }
    }
}
