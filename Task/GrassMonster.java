public class GrassMonster extends Monster {
    public GrassMonster(String name, MonsterSpecies species, int level, int hp, int attackPower) {
        super(name, species, hp, level, attackPower);
    }

    @Override
    public void attack(Monster target) {
        int damage = this.attackPower;
        if (target.getSpecies().getType().equals("Water")) {
            damage += 10;
        }
        target.takeDamage(damage);
    }
}