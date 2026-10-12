package stack;

public class ExplorationStack // Stack location operations 
{

    private StackNode top; // Top node of the stack
    private StackNode histtop;

    // Check whether the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    //Check if a location already exists in the stack
    private boolean isInHistory(String location) {
        StackNode current = histtop;
        while (current != null) {
            if (current.location.equalsIgnoreCase(location)) {
                return true; // Already visited earlier
            }
            current = current.next;
        }
        return false;
    }

    // Add a location to the stack
    public void push(String location) {
        StackNode newNode = new StackNode(location);

        newNode.next = top;
        top = newNode;
        if(!isInHistory(location)) {
            StackNode hisNode = new StackNode(location);
            hisNode.next = histtop;
            histtop = hisNode;
        }
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

        System.out.println("Current exploration path(latest first):");

        while (current != null) {
            System.out.println(current.location);
            current = current.next;
        }

        System.out.println("\nAll bypassed locations: ");
        current=histtop;
        while(current!=null) {
            System.out.println(current.location);
            current = current.next;
        }
    }
}
