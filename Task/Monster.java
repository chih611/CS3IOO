
// Abstract base class for all monster types

public abstract class Monster {
    // Protected attributes accessible to within the class itself and to any
    // subclasses
    protected String name;
    protected MonsterSpecies species;
    protected int level;
    protected int hp;
    protected int attackPower;

    // Constructor
    public Monster(String name, MonsterSpecies species, int level, int hp, int attackPower) {
        this.name = name;
        this.species = species;
        this.hp = hp;
        this.level = level;
        this.attackPower = attackPower;
    }

    // Abstract attack method to be implemented by subclasses
    public abstract void attack(Monster target);

    // Reduces monster's HP by the given amount
    public void takeDamage(int amount) {
        this.hp -= amount;
        if (this.hp < 0) {
            this.hp = 0; // Ensure HP doesn't go negative
        }
    }

    // Checks if the monster has fainted (HP <= 0)
    public boolean isFainted() {
        return this.hp <= 0;
    }

    // Returns a string representation of the monster
    @Override
    public String toString() {
        return name + " (" + species + ", Lv." + ", HP: " + hp + ")";
    }

    // Getter methods for controlled access to attributes
    public String getName() {
        return name;
    }

    public MonsterSpecies getSpecies() {
        return species;
    }

    public int getHp() {
        return hp;
    }

    public int getLevel() {
        return level;
    }

    public int getAttackPower() {
        return attackPower;
    }
}