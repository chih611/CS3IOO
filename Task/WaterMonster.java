public class WaterMonster extends Monster {
    public WaterMonster(String name, MonsterSpecies species, int level, int hp, int attackPower) {
        super(name, species, hp, level, attackPower);
    }

    @Override
    public void attack(Monster target) {
        int damage = this.attackPower;
        if (target.getSpecies().getType().equals("Fire")) {
            damage += 10;
        }
        target.takeDamage(damage);
    }
}