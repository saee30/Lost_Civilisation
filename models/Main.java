package models;
import bst.*;
import graph.*;
import queue.*;
import stack.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        // Object creation
        Inventory playerinv = new Inventory();
        CivilisationMap map = Initialisation.createMap();
        ExplorationStack es = new ExplorationStack();
        ArtifactBST archive = new ArtifactBST();
        ExcavationQueue exqueue = new ExcavationQueue();

        // Lists required by the ExcavationProcessor
        ArrayList<Location> locations = new ArrayList<>();
        ArrayList<Artifact> artifacts = new ArrayList<>();

        // Populate locations
        for (int i = 1; i <= Initialisation.NUMBER_OF_LOCATIONS; i++) {
            locations.add(new Location(
                i,
                Initialisation.getLocationName(i),
                ""
            ));
        }

        // Populate the seven relics
        for (int i = 1; i <= 7; i++) {
            Artifact a = Initialisation.getArtifact(i);
            if (a != null) {
                artifacts.add(a);
            }
        }

        // Create the processor AFTER populating both lists
        ExcavationProcessor processor = new ExcavationProcessor(
            exqueue, locations, artifacts, archive, playerinv
        );

        int tokens=Initialisation.STARTING_TOKENS;

        //Setting starting point
        int currLoc=1;
        es.push(Initialisation.getLocationName(currLoc));
        //Counter to track no. of artifacts found
        int counter=0;

        //Flag array to keep a track of which artifact has been collected
        boolean[] collected=new boolean[Initialisation.NUMBER_OF_LOCATIONS+1];
        Arrays.fill(collected, false);

        System.out.println("Welcome to Lost Civilisation: The Forgotten Kingdom");
        System.out.println("Your journey to the ancient ruins begins now. Currently you are at "+es.peek());
        System.out.println("Uncover the 7 relics to unlock the Forgotten library");

        boolean game=true;
        
        while(game) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Current location: "+currLoc+" '"+Initialisation.getLocationName(currLoc)+"'");
            System.out.println("No. of artifacts recovered: "+ counter +"\nHint tokens remaining: "+tokens+"\n");
            System.out.println("1. Investigate the current location");
            System.out.println("2. Travel to a new location");
            System.out.println("3. Display the entire map");
            System.out.println("4. Go back to previous location");
            System.out.println("5. View all bypassed locations");
            System.out.println("6. View your inventory");
            System.out.println("7. View pending excavation requests");
            System.out.println("8. Process next excavation request");
            System.out.println("9. To abandon your quest");
            System.out.println("Enter choice (1-7)");
            int choice=0;
            while(true) {
                try {
                    choice=sc.nextInt();
                    sc.nextLine();
                    break;   
                }
                catch(InputMismatchException e) {
                    System.out.println("Enter an integer");
                    sc.nextLine();
                }
            }

            switch(choice) {
                case 1: {
                    if(currLoc==8) {
                        System.out.println("You are now at the final destination: The Forgotten Library");
                        if(counter<7) {
                            System.out.println("You haven't collected all the artifacts yet, try again");
                            System.out.println("You have "+counter+" artifacts");
                            break;
                        }

                        int verified = 0;
                        System.out.println("You are now entering The Forgotten Library");
                        System.out.println("Authenticate the 7 Artifact IDs");

                        // Track which IDs have already been verified to prevent duplicate entry
                        boolean[] alreadyEntered = new boolean[8];

                        for (int i = 1; i <= 7; i++) {
                            System.out.print("Enter the ID for relic " + i + ": ");
                            int uid = 0;
                            while (true) {
                                try {
                                    uid = sc.nextInt();
                                    sc.nextLine(); 
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter an integer!");
                                    sc.nextLine();
                                }
                            }

                            // Check bounds and whether it was already typed
                            if (uid >= 1 && uid <= 7 && alreadyEntered[uid]) {
                                System.out.println("You already authenticated Relic ID " + uid + "! Enter a different one.");
                                i--; // Retry this slot
                            } else if (archive.search(uid)) {
                                System.out.println("Relic ID " + uid + " authenticated in Archive!");
                                if (uid >= 1 && uid <= 7) {
                                    alreadyEntered[uid] = true;
                                }
                                verified++;
                            } else {
                                System.out.println("Invalid artifact number (" + uid + "), not found in Archive.");
                                i--; // Retry this slot
                            }
                        }

                        // ============================================================
                        // CHECK OUTSIDE THE LOOP (Only after all 7 prompts are done)
                        // ============================================================
                        if (verified < 7) {
                            System.out.println("Recheck your inventory for missing artifacts.");
                            break; // Exit case 1 back to the main menu
                        }

                            System.out.println("\n\nWelcome to the final stage");
                            System.out.println(Initialisation.FINAL_INTRODUCTION);
                            System.out.println(Initialisation.FINAL_CLUE);
                            System.out.println("Enter your answer");
                            String ans=sc.nextLine().toLowerCase().trim();

                            if(ans.equals(Initialisation.FINAL_ANSWER)) {
                                System.out.println("BINGO!! QUEST COMPLETE!! THE LOST CIVILISATION'S SECRETS HAVE BEEN DISCOVERED");
                                System.out.println("Total points gained: "+playerinv.totalValue());
                            }
                            else {
                                System.out.println("Incorrect keyword. The civilisation's secrets remain undiscovered");
                            }
                            break;
                    }
                    

                    //For relics 1 to 7
                    if (collected[currLoc]) {
                        System.out.println("You have already retrieved the ancient relic from this site.");
                        break;
                    }

                    Initialisation.PuzzleData puzzle= Initialisation.getPuzzle(currLoc);
                    Artifact relic= Initialisation.getArtifact(currLoc);

                    if (puzzle == null || relic == null) {
                        System.out.println("No relics available at this location");
                        break;
                    }
                    
                    System.out.println(puzzle.introduction);
                    System.out.println("Clue:\n" + puzzle.clue);
                    System.out.println("Relic at stake: " + relic.getName() + " [" + relic.getRarity() + "]");
                    
                    int hintIndex=0;
                    boolean solved=false;
                    boolean exited=false;
                    int attempts=0;

                    while((!solved)&&(!exited)&&(attempts<Initialisation.MAX_ATTEMPTS)) {
                        System.out.println("1. Enter answer\n2. Get a hint for 1 token\n3. Exit");
                        int ch=0;
                        while(true) {
                            try{
                                ch=sc.nextInt();
                                sc.nextLine();
                                break;
                            }
                            catch(InputMismatchException e) {
                                System.out.println("Enter an integer");
                                sc.nextLine();
                            }
                        }
                        switch (ch) {
                            case 1: {
                                System.out.println("Enter your answer");
                                String ans=sc.nextLine().trim().toLowerCase();

                                boolean match = false;
                                for (String acc : puzzle.acceptedAnswers) {
                                    if (ans.equals(acc.toLowerCase())) {
                                        match = true;
                                        break;
                                    }
                                }

                                if (match) {
                                    System.out.println("Correct answer! Excavation request added to queue.");
                                    // Add the request to the queue
                                    exqueue.enqueue(currLoc, Initialisation.getLocationName(currLoc), puzzle.clue);
                                    System.out.println("Queue size: " + exqueue.size());
                                    // Do NOT process it here.
                                    // Option 8 will process the request later.
                                    solved = true;
                                }
                                else {
                                    attempts++;
                                    System.out.println("Incorrect answer.\nAttempts left: "+ (Initialisation.MAX_ATTEMPTS-attempts));
                                }

                            }
                            break;
                            
                            case 2: {
                                if(tokens<2) {
                                    System.out.println("Hints unavailable, not enough tokens");
                                }
                                else if (hintIndex >= puzzle.hints.length) {
                                System.out.println("No further hints available for this puzzle.");
                                } 
                                else {
                                    tokens--;
                                    System.out.println("HINT: " + puzzle.hints[hintIndex++]);
                                    System.out.println("Tokens remaining: " + tokens);
                                }
                            }
                            break;

                            case 3: {
                                System.out.println("Walking away");
                                exited=true;
                            }
                            break;

                            default: {
                                System.out.println("Incorrect input, try again");
                            }
                            break;
                        }
                    }

                    if ((!solved) && (!exited) && (attempts >= Initialisation.MAX_ATTEMPTS)) {
                        System.out.println("Maximum attempts reached! You must leave and return later.");
                    }
                }
                
                break;

                case 2: {
                    System.out.println("Connected paths: ");
                    List<Integer> neighbors=map.getNeighbors(currLoc);                   
                    
                    if (neighbors == null || neighbors.isEmpty()) {
                        System.out.println("No paths found leading out of this location.");
                        break;
                    }

                    String curr = es.pop();
                    String prev = es.peek();
                    es.push(curr);

                    for (int i = 0; i < neighbors.size(); i++) {
                        if (prev != null && Initialisation.getLocationName(neighbors.get(i)).equalsIgnoreCase(prev)) {
                            neighbors.remove(i);
                            break;
                        }
                    }

                    if (neighbors.isEmpty()) {
                        System.out.println("Dead end! Use Option 4 (Backtrack) to return to " + prev);
                        break;
                    }

                    // Display all reachable neighbors
                    for (int i = 0; i < neighbors.size(); i++) {
                        int destId = neighbors.get(i);
                        System.out.println((i + 1) + ". " + Initialisation.getLocationName(destId));
                    }

                    System.out.print("Select destination number (1-" + neighbors.size() + "): ");
                    int move= 0;
                    while (true) {
                        try {
                            move = sc.nextInt();
                            sc.nextLine(); 
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a number.");
                            sc.nextLine();
                            break;
                        }
                    }
                    
                    if((move>=1)&&(move<=neighbors.size())) {
                        currLoc=neighbors.get(move-1);
                        es.push(Initialisation.getLocationName(currLoc));
                        System.out.println("Moved to: "+currLoc+", "+Initialisation.getLocationName(currLoc));
                    }
                    else {
                        System.out.println("Incorrect destination selected, try again later");
                    }
                }
                break;

                case 3: {
                    map.displayMap();
                }
                break;

                case 4: {
                    String curr=es.pop();
                    String prev=es.peek();

                    if((curr!=null)&&(prev!=null)) {
                        System.out.println("Departed from: "+curr);
                        System.out.println("Returned to: "+prev);

                        //Resetting currLoc to prev's id
                        for(int i=1;i<=Initialisation.NUMBER_OF_LOCATIONS;i++) {
                            if(prev.equals(Initialisation.getLocationName(i))) {
                                currLoc=i;
                                break;
                            }
                        }
                    }
                    else {
                        currLoc = 1;
                        es.push(Initialisation.getLocationName(1));
                        System.out.println("You are at your expedition start point ("+ Initialisation.getLocationName(1) + ")! Cannot backtrack further.");
                    }
                }
                break;

                case 5: {
                    es.display();
                }
                break;

                case 6: {
                    archive.inorder();
                }
                break;

                case 7: {
                    System.out.println("Queue size: " + exqueue.size());
                    exqueue.displayQueue();
                }
                break;
                
                case 8: {
                    if (exqueue.isEmpty()) {
                        System.out.println("No pending excavation requests.");
                        break;
                    }
                    // Find the next location before processing its request so Main can update its own game state afterwards.
                    int nextLocationId = -1;
                    // The queue does not currently expose its front node
                    nextLocationId = exqueue.peekLocationId();
                    ArrayList<Artifact> found = processor.processNext();
                    if (!found.isEmpty()) {
                        collected[nextLocationId] = true;
                        counter += found.size();
                        System.out.println("Excavation successful! Relics recovered: " + found.size());
                    }
                    break;
                }
                
                case 9: {
                    game=false;
                    System.out.println("Quest abandoned");
                }
                break;

                default: {
                    System.out.println("Incorrect choice, enter a number between 1 to 7");
                }
                break;
             
            }
        } 
        sc.close();
    }
}