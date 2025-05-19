public class MonsterSpecies {
    // Private attributes
    private String code;
    private String name;
    private String type;
    private int level;
    private int baseHP;
    private int baseAttack;

    // Constructor
    public MonsterSpecies(String code, String name, String type, int level, int baseHP, int baseAttack) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.level = level;
        this.baseHP = baseHP;
        this.baseAttack = baseAttack;
    }

    // Returns a string representation of the species (just the name for
    // Monster.toString compatibility)
    @Override
    public String toString() {
        return name;
    }

    // Getter methods for controlled access
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public int getBaseAttack() {
        return baseAttack;
    }
}