package models;

public class Artifact {
    private int artifactId;
    private String name;
    private String type;
    private int age;
    private String rarity;    // COMMON, UNCOMMON, RARE, LEGENDARY
    private int value;
    private String location;
    private String description;

    public Artifact(int artifactId, String name, String type, int age, String rarity, int value, String location, String description) {
        this.artifactId = artifactId;
        this.name = name;
        this.type = type;
        this.age = age;
        this.rarity = rarity;
        this.value = value;
        this.location = location;
        this.description = description;
    }

    // Getters
    public int getArtifactId() { return artifactId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getAge() { return age; }
    public String getRarity() { return rarity; }
    public int getValue() { return value; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    
    public String toString() {
        return name + " (ID: " + artifactId + ")\n" +
               "   Type: " + type + " | Origin: " + location + "\n" +
               "   Rarity: " + rarity + " | Age: " + age + " years\n" +
               "   Value: " + value + "\n" +
               "   Desc: " + description;
    }
}
