package models;
import graph.CivilisationMap;
import graph.Location;

public class Initialisation {

    // ================= GAME SETTINGS =================

    public static final int STARTING_TOKENS = 10;
    public static final int MAX_ATTEMPTS = 3;
    public static final int NUMBER_OF_LOCATIONS = 8;

    public static final Artifact[] ARTIFACTS = {
        new Artifact(1, "Golden Crown", "Royal Relic",
            1200, "Legendary", 1000, "King's Fort",
            "A crown worn by the rulers of the forgotten kingdom."),

        new Artifact(2, "Sun Stone", "Religious Relic",
            1100, "Rare", 800, "Sun Temple",
            "A stone used in ancient solar ceremonies."),

        new Artifact(3, "Secret Scroll", "Manuscript",
            1000, "Rare", 700, "Old Archives",
            "A scroll containing records of the kingdom's past."),

        new Artifact(4, "Blue Gem", "Gemstone",
            900, "Very Rare", 900, "Crystal Cave",
            "A blue gemstone concealed in an underground chamber."),

        new Artifact(5, "Silver Cup", "Ceremonial Object",
            950, "Uncommon", 500, "Old Stepwell",
            "A silver cup used during royal ceremonies."),

        new Artifact(6, "Old Compass", "Navigation Tool",
            850, "Rare", 650, "Lost Harbour",
            "A compass once used by the kingdom's sailors."),

        new Artifact(7, "Star Lens", "Astronomical Instrument",
            800, "Very Rare", 850, "Star Tower",
            "A lens used by ancient astronomers.")
    };

    // ================= PUZZLE DATA =================

    public static class PuzzleData {
        public int locationId;
        public String introduction;
        public String clue;
        public String answer;
        public String[] acceptedAnswers;
        public String[] hints;

        public PuzzleData(int locationId, String introduction,
                          String clue, String answer,
                          String[] acceptedAnswers, String[] hints) {
            this.locationId = locationId;
            this.introduction = introduction;
            this.clue = clue;
            this.answer = answer;
            this.acceptedAnswers = acceptedAnswers;
            this.hints = hints;
        }
    }

    public static final PuzzleData[] PUZZLES = {

        new PuzzleData(1,
            "A sealed royal dispatch lies inside the throne room.",
            "Decode FURZQ. Each letter was shifted three places forward.",
            "crown",
            new String[]{"crown", "the crown"},
            new String[]{
                "Encryption moved each original letter forward; reverse that operation.",
                "The first decoded letter is C. The last decoded letter is N."
            }),

        new PuzzleData(2,
            "An astronomical mechanism guards the temple altar.",
            "Find the next value: 2, 6, 12, 20, 30, ?",
            "42",
            new String[]{"42"},
            new String[]{
                "Examine the differences between consecutive numbers.",
                "The differences are 4, 6, 8, 10. Continue the pattern."
            }),

           new PuzzleData(3,
            "A sealed royal dispatch lies inside the throne room.",
            "I hold the stories of the past, but I never speak. "
            + "You turn my pages to uncover secrets. What am I?",
            "book",
            new String[]{"book", "a book"},
            new String[]{
                "You can find me in a library.",
                "I contain pages filled with words."
            }),

        new PuzzleData(4,
            "A voice seems to answer you from deep inside the cave.",
            "I repeat what you say, but I have no mouth. What am I?",
            "echo",
            new String[]{"echo", "an echo"},
            new String[]{
                "You may hear me in mountains or caves.",
                "I am your voice coming back to you."
            }),

        new PuzzleData(5,
            "An old watchman left a clue at the stepwell. It said: \n",
            "I was standing at the top of the stepwell, facing towards the rising sun. I turned right twice, in order to check for a new visitor."
            + "What direct am I facing now?",
            "west",
            new String[]{"west", "the west"},
            new String[]{
                "Identify which cardinal direction the rising sun represents, then trace the watchman's turns.",
                "Facing East and turning right once points you South. A second right turn rotates you 180 degrees from where you started."
            }),

        new PuzzleData(6,
            "Three ships left the harbour before the invasion.",
            "The Lotus left before the Falcon. "
            + "The Falcon left before the Serpent. "
            + "Which ship left last?",
            "serpent",
            new String[]{"serpent", "the serpent"},
            new String[]{
                "Put the ships in departure order.",
                "The Serpent left after both other ships."
            }),

        new PuzzleData(7,
            "The Star Lens disappeared from the observatory.",
            "The tower was locked from the inside. "
            + "The only key was found with the guard, "
            + "who never entered the tower. "
            + "Can the guard alone be proven responsible?",
            "no",
            new String[]{"no", "no, cannot be proven"},
            new String[]{
                "Possessing the key does not prove someone used it.",
                "There is not enough evidence to prove the guard did it."
            }),
    };

    // ================= LOCATION DATA =================

    private static final String[] LOCATION_NAMES = {
        "King's Fort",
        "Sun Temple",
        "Old Archives",
        "Crystal Cave",
        "Old Stepwell",
        "Lost Harbour",
        "Star Tower",
        "The Forgotten Library"
    };

    private static final String[] LOCATION_DESCRIPTIONS = {
        "Ruins of the royal stronghold, where the king's final order remains sealed.",
        "An abandoned temple containing records of ancient astronomical observations.",
        "A damaged archive holding fragments of the kingdom's chronology.",
        "An underground chamber concealing a gemstone behind a logical trial.",
        "An ancient stepwell with a mechanism controlled by a water calculation.",
        "A deserted harbour containing the kingdom's old navigation records.",
        "An astronomical tower where the final observation was encoded.",
        "The last surviving library, where the kingdom's final secret is hidden."
    };

    // ================= FINAL LIBRARY =================

    public static final String FINAL_INTRODUCTION =
        "The seven relics have brought you to the kingdom's last library.";

    public static final String FINAL_CLUE =
        "The final vault opens with the name of the civilisation "
        + "whose history you have reconstructed.";

    public static final String FINAL_ANSWER = "kingdom";

    public static final String[] FINAL_HINTS = {
        "The answer refers to the civilisation, not an individual relic.",
        "It is the word used in the title The Forgotten Kingdom."
    };

    // ================= INITIALISE THE MAP =================

    public static CivilisationMap createMap() {
        CivilisationMap map = new CivilisationMap();

        for (int i = 0; i < NUMBER_OF_LOCATIONS; i++) {
            Location location = new Location(
                i + 1,
                LOCATION_NAMES[i],
                LOCATION_DESCRIPTIONS[i]
            );

            map.addLocation(location);
        }

        // Connections between locations
        map.addEdge(0, 1);
        map.addEdge(1, 2);
        map.addEdge(1, 7);
        map.addEdge(2, 3);
        map.addEdge(2, 4);
        map.addEdge(3, 5);
        map.addEdge(4, 5);
        map.addEdge(5, 6);
        map.addEdge(6, 7);
        map.addEdge(7, 8);

        return map;
    }

    // ================= RETRIEVE CONFIGURATION =================

    public static Artifact getArtifact(int id) {
        for (Artifact artifact : ARTIFACTS) {
            if (artifact.artifactId == id) {
                return artifact;
            }
        }
        return null;
    }

    public static PuzzleData getPuzzle(int locationId) {
        for (PuzzleData puzzle : PUZZLES) {
            if (puzzle.locationId == locationId) {
                return puzzle;
            }
        }
        return null;
    }

    public static String getLocationName(int locationId) {
        if (locationId < 1 || locationId > NUMBER_OF_LOCATIONS) {
            return null;
        }
        return LOCATION_NAMES[locationId - 1];
    }

    // ================= DISPLAY WORLD INFORMATION =================

    public static void displayLocations() {
        for (int i = 0; i < NUMBER_OF_LOCATIONS; i++) {
            System.out.println(
                (i + 1) + ". " + LOCATION_NAMES[i]
            );
            System.out.println(LOCATION_DESCRIPTIONS[i]);
            System.out.println();
        }
    }
}
