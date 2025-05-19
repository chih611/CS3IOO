public class Player {
    // Private attributes
    private String playerId;
    private String name;
    private Monster[] team;
    private int monsterCount;

    // Constructor
    public Player(String playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.team = new Monster[5]; // Up to 5 monsters
        this.monsterCount = 0;
    }

    // Adds a monster to the team
    public void addMonster(Monster monster) {
        // Check if team is full
        if (monsterCount >= 5) {
            return; // Team is full, do nothing
        }
        // Check for duplicates (same name and species)
        for (int i = 0; i < monsterCount; i++) {
            if (team[i].getName().equals(monster.getName()) &&
                team[i].getSpecies().equals(monster.getSpecies())) {
                return; // Duplicate monster, do nothing
            }
        }
        // Add monster to team
        team[monsterCount] = monster;
        monsterCount++;
    }

    // Returns true if at least one monster hasn't fainted
    public boolean hasAliveMonster() {
        for (int i = 0; i < monsterCount; i++) {
            if (!team[i].isFainted()) {
                return true;
            }
        }
        return false;
    }

    // Gets the next available monster for battle
    public Monster getNextAliveMonster() {
        for (int i = 0; i < monsterCount; i++) {
            if (!team[i].isFainted()) {
                return team[i];
            }
        }
        return null; // No alive monsters
    }

    // Returns a string representation of the player and their team
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (ID: ").append(playerId).append(")\n");
        if (monsterCount == 0) {
            sb.append("No monsters in team.\n");
        } else {
            for (int i = 0; i < monsterCount; i++) {
                sb.append(team[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // Getter methods
    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Monster[] getTeam() {
        return team;
    }

    public int getMonsterCount() {
        return monsterCount;
    }
}