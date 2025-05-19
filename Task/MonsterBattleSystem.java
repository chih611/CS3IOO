import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MonsterBattleSystem {
    // Private attributes
    private MonsterSpecies[] speciesList;
    private Player[] players;
    private int speciesCount;
    private int playerCount;

    // Constructor
    public MonsterBattleSystem() {
        this.speciesList = new MonsterSpecies[50]; // Max 50 species
        this.players = new Player[100]; // Max 100 players
        this.speciesCount = 0;
        this.playerCount = 0;
    }

    // Adds a new species to the species list
    public void addSpecies(String code, String name, String type, int level, int baseHP, int baseAttack) {
        for (int i = 0; i < speciesCount; i++) {
            if (speciesList[i].getCode().equals(code)) {
                return; // Duplicate code, do nothing
            }
        }
        if (speciesCount >= 50) {
            return;
        }
        speciesList[speciesCount] = new MonsterSpecies(code, name, type, level, baseHP, baseAttack);
        speciesCount++;
    }

    // Adds a new player
    public void addPlayer(String playerId, String name) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getPlayerId().equals(playerId)) {
                return; // Duplicate ID, do nothing
            }
        }
        if (playerCount >= 100) {
            return;
        }
        players[playerCount] = new Player(playerId, name);
        playerCount++;
    }

    // Assigns a monster of a chosen species to a player
    public void assignMonster(String playerId, String speciesCode) {
        Player player = null;
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getPlayerId().equals(playerId)) {
                player = players[i];
                break;
            }
        }
        if (player == null) {
            return; // Player not found
        }
        MonsterSpecies species = null;
        for (int i = 0; i < speciesCount; i++) {
            if (speciesList[i].getCode().equals(speciesCode)) {
                species = speciesList[i];
                break;
            }
        }
        if (species == null) {
            return; // Species not found
        }
        Monster monster;
        switch (species.getType()) {
            case "Fire":
                monster = new FireMonster(species.getName(), species, species.getLevel(), species.getBaseHP(),
                        species.getBaseAttack());
                break;
            case "Water":
                monster = new WaterMonster(species
                        .getName(), species, species.getLevel(), species.getBaseHP(),
                        species.getBaseAttack());
                break;
            case "Grass":
                monster = new GrassMonster(species
                        .getName(), species, species.getLevel(), species.getBaseHP(),
                        species.getBaseAttack());
                break;
            case "Thunder":
                monster = new ThunderMonster(species
                        .getName(), species, species.getLevel(), species.getBaseHP(),
                        species.getBaseAttack());
                break;
            default:
                return; // Invalid type
        }
        player.addMonster(monster);
    }

    // Simulates a battle between two players
    public String battle(String player1ID, String player2ID) {
        Player player1 = null;
        Player player2 = null;
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getPlayerId().equals(player1ID)) {
                player1 = players[i];
            }
            if (players[i].getPlayerId().equals(player2ID)) {
                player2 = players[i];
            }
        }
        if (player1 == null || player2 == null) {
            return "Battle cannot start: One or both players not found.";
        }
        if (!player1.hasAliveMonster() || !player2.hasAliveMonster()) {
            return "Battle cannot start: One or both players have no alive monsters.";
        }

        StringBuilder battleLog = new StringBuilder();
        Monster m1 = player1.getNextAliveMonster();
        Monster m2 = player2.getNextAliveMonster();

        // Pre-checks
        battleLog.append("\n");
        battleLog.append("=== Battle Pre-checks ===\n");
        battleLog.append(player1.getName()).append("'s Monster [").append(m1.getName())
                .append("] HP: ").append(m1.getHp()).append(" - Dame: ").append(m2.attackPower).append(" Fainted?: ")
                .append(m1.isFainted()).append("\n");
        battleLog.append(player2.getName()).append("'s Monster [").append(m2.getName())
                .append("] HP: ").append(m2.getHp()).append(" - Dame: ").append(m2.attackPower).append(" Fainted?: ")
                .append(m2.isFainted()).append("\n");
        battleLog.append("\n");
        battleLog.append("=== BATTLE START ===\n");

        int round = 1;
        while (player1.hasAliveMonster() && player2.hasAliveMonster()) {
            battleLog.append("Round ").append(round).append(":\n");
            // Player 1's monster attacks
            battleLog.append(m1.getName()).append(" (").append(m1.getSpecies().getType())
                    .append(") attacks ").append(m2.getName()).append(" (").append(m2.getSpecies().getType())
                    .append(") ");
            m1.attack(m2); // Use the subclass attack method
            battleLog.append(m2.getName()).append(" now has ").append(m2.getHp()).append(" HP.\n");

            // Player 2's monster attacks if still alive
            if (!m2.isFainted()) {
                battleLog.append(m2.getName()).append(" (").append(m2.getSpecies().getType())
                        .append(") attacks ").append(m1.getName()).append(" (").append(m1.getSpecies().getType())
                        .append(") ");
                m2.attack(m1); // Use the subclass attack method
                battleLog.append(m1.getName()).append(" now has ").append(m1.getHp()).append(" HP.\n");
            }

            if (m2.isFainted() && player2.hasAliveMonster() == false) {
                battleLog.append(m2.getName()).append(" has fainted!\n");
                battleLog.append("Winner: ").append(player1.getName()).append(" - ").append(player1ID).append("\n");
                break;
            } else if (m1.isFainted() && player1.hasAliveMonster() == false) {
                battleLog.append(m1.getName()).append(" has fainted!\n");
                battleLog.append("Winner: ").append(player2.getName()).append(" - ").append(player2ID).append("\n");
                break;
            }
            round++;
            m1 = player1.getNextAliveMonster();
            m2 = player2.getNextAliveMonster();
        }

        return battleLog.toString();
    }

    // Returns a string representation of the system
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== BATTLE SUMMARY ===\n");
        sb.append("=== MONSTER SPECIES ===\n");
        for (int i = 0; i < speciesCount; i++) {
            sb.append(speciesList[i].getCode()).append(": ")
                    .append(speciesList[i].toString()).append(" [")
                    .append(speciesList[i].getType()).append("] (HP: ")
                    .append(speciesList[i].getBaseHP()).append(", ATK: ")
                    .append(speciesList[i].getBaseAttack()).append(")\n");
        }
        sb.append("=== PLAYERS ===\n");
        for (int i = 0; i < playerCount; i++) {
            sb.append(players[i].getPlayerId()).append(" - ").append(players[i].getName()).append("\n");
            for (int j = 0; j < players[i].getMonsterCount(); j++) {
                Monster m = players[i].getTeam()[j];
                sb.append("  > ").append(m.getName()).append(" (")
                        .append(m.getSpecies().toString())
                        .append(", HP: ").append(m.getHp()).append(")\n");
            }
        }
        return sb.toString();
    }

    public void saveData() throws Exception {
        // Save species
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("MBQ-Species.txt"))) {
            for (int i = 0; i < speciesCount; i++) {
                MonsterSpecies species = speciesList[i];
                writer.write(species.getCode() + ";" + species.toString() + ";" + species.getType() + ";"
                        + species.getLevel() +
                        ";" + species.getBaseHP() + ";" + species.getBaseAttack());
                writer.newLine();
            }
        }
        // Save players
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("MBQ-Players.txt"))) {
            for (int i = 0; i < playerCount; i++) {
                Player player = players[i];
                writer.write(player.getPlayerId() + ";" + player.getName());
                writer.newLine();
            }
        }
        // Save monsters
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("MBQ-Monsters.txt"))) {
            for (int i = 0; i < playerCount; i++) {
                Player player = players[i];
                for (int j = 0; j < player.getMonsterCount(); j++) {
                    Monster monster = player.getTeam()[j];
                    writer.write(player.getPlayerId() + ";" + monster.getName() + ";" +
                            monster.getSpecies().getCode() + ";" + monster.getLevel() +
                            ";" +
                            monster.getHp() + ";" + monster.getAttackPower());
                    writer.newLine();
                }
            }
        }
    }

    public void loadData() throws Exception {
        // Load species
        try (BufferedReader reader = new BufferedReader(new FileReader("MBQ-Species.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    addSpecies(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                }
            }
        }

        // Load players
        try (BufferedReader reader = new BufferedReader(new FileReader("MBQ-Players.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    addPlayer(parts[0], parts[1]);
                }
            }
        }

        // Load monsters
        try (BufferedReader reader = new BufferedReader(new FileReader("MBQ-Monsters.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    assignMonster(parts[0], parts[2]);
                    // Find the player and update monster stats
                    for (int i = 0; i < playerCount; i++) {
                        if (players[i].getPlayerId().equals(parts[0])) {
                            for (int j = 0; j < players[i].getMonsterCount(); j++) {
                                Monster m = players[i].getTeam()[j];
                                if (m.getName().equals(parts[1])) {
                                    m.hp = Integer.parseInt(parts[4]); // Set HP
                                    m.attackPower = Integer.parseInt(parts[5]); // Set attackPower
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public void displayPlayerInfo(String playerId) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getPlayerId().equals(playerId)) {
                System.out.println("Player Info for P" + playerId + " - " + players[i].getName());
                for (int j = 0; j < players[i].getMonsterCount(); j++) {
                    Monster m = players[i].getTeam()[j];
                    System.out.println(
                            "  > " + m.getName() + " (" + m.getSpecies().toString() + ", HP: " + m.getHp() + ")");
                }
                return;
            }
        }
        System.out.println("Player P" + playerId + " not found.");
    }

    // Getter methods
    public MonsterSpecies[] getSpeciesList() {
        return speciesList;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getSpeciesCount() {
        return speciesCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}