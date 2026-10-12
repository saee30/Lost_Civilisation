package stack;

class StackNode // Represents a particular location in the game 
{
    String location; // Location visited current 
    StackNode next; // next points to previous location visited 

    StackNode(String location) {
        this.location = location;
        this.next = null;
    }
}
