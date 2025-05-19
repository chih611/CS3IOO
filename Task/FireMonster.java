public class FireMonster extends Monster {
    public FireMonster(String name, MonsterSpecies species, int level, int hp, int attackPower) {
        super(name, species, level, hp, attackPower);
    }

    @Override
    public void attack(Monster target) {
        int damage = this.attackPower;
        if (target.getSpecies().getType().equals("Grass")) {
            damage += 10;
        }
        target.takeDamage(damage);
    }
}