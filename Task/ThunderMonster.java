public class ThunderMonster extends Monster {
    // Flag to track stun status and turns remaining
    private boolean isStunned;
    private int stunTurns;

    public ThunderMonster(String name, MonsterSpecies species, int level, int hp, int attackPower) {
        super(name, species, level, hp, attackPower);
        this.isStunned = false;
        this.stunTurns = 0;
    }

    @Override
    public void attack(Monster target) {
        // Base damage
        int damage = this.attackPower;
        target.takeDamage(damage);

        // // 50% chance to stun
        // if (Math.random() < 0.5) {
        // if (!this.isStunned) {
        // this.isStunned = true;
        // this.stunTurns = 1;
        // this.attackPower -= 20; // Reduce attack power by 20 if stunned
        // System.out.println(this.getName() + " unleashes Thunder Strike and stuns " +
        // target.getName() + "!");
        // }
        // } else {
        // System.out.println(this.getName() + " unleashes Thunder Strike for " + damage
        // + " damage!");
        // }
    }

    // Override to update stun status each turn
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (isStunned && stunTurns > 0) {
            stunTurns--;
            if (stunTurns == 0) {
                isStunned = false;
                attackPower += 20; // Restore attack power after stun wears off
                System.out.println(getName() + " recovers from stun!");
            }
        }
    }

    @Override
    public String toString() {
        return name + " (ThunderMonster, Thunder Charge, HP: " + hp + ")";
    }
}