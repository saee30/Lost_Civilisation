package models;

class Node {
    Artifact data;
    Node next;

    Node (Artifact a) {
        this.data=a;
    }
}

public class Inventory {
    Node head;
    int count;

    public Inventory() {
        head=null;
        count=0;
    }

    //Check if artifact is already present
    public boolean contain(int artifactId) {
        Node curr=head;
        while(curr!=null) {
            if (curr.data.getArtifactId()==artifactId) {
                return true;
            }
            curr=curr.next;
        }
        return false;
    }

    public boolean addArtifact(Artifact a) {
        if(a==null) return false;

        if(contain(a.getArtifactId())) {
            System.out.println("Artifact already present!!");
            return false;
        } 

        Node temp=new Node(a);
        temp.next=head;
        head=temp;
        count++;
        return true;
    }

    public boolean removeArtifact(Artifact a) {
        if((head==null)||(a==null)) {
            System.out.println("Inventory is empty!!");
            return false;
        }

        if (a.getArtifactId() == head.data.getArtifactId()) {
            head=head.next;
            count--;
            System.out.println("Artifact removed");
            return true;
        }

        Node curr=head;
        while((curr.next!=null) && (a.getArtifactId()!=curr.next.data.getArtifactId())) {
            curr=curr.next;
        }

        if(curr.next!=null) {
            curr.next=curr.next.next;
            count--;
            System.out.println("Artifact removed");   
            return true;
        }

        System.out.println("Artifact not found");
        return false;
    }

    public Artifact searchArtifact(int id) {
        Node curr=head;
        while (curr!=null) {
            if (curr.data.getArtifactId()==id) {
                return curr.data;
            }
            curr = curr.next;
        }
        return null;
    }

    public int number() {
        return count;
    }

    public int totalValue() {
        int val=0;
        Node curr=head;

        while(curr!=null) {
            val+=curr.data.getValue();
            curr=curr.next;
        }

        return val;
    }

    public void display() {
        if(head==null) {
            System.out.println("Inventory is empty.\n0 Artifacts found");
        }

        else {
            Node curr=head;
            int i=1;
            while(curr!=null) {
                System.out.println("Artifact "+i++ + "\n"+curr.data.getName()+"\nRarity: "+curr.data.getRarity()+"\nValue: "+curr.data.getValue()+"\n");
                curr=curr.next;
            }
            System.out.println("Total artifacts: "+count);
            System.out.println("Total value: "+totalValue());
        }
    }
}
