import java.util.Scanner;

public class MonsterBattleTester {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        // Create a instance of the battle system
        MonsterBattleSystem system = new MonsterBattleSystem();

        // // Add species to the system
        // system.addSpecies("FD001", "FireDragon", "Fire", 100, 70);
        // system.addSpecies("WD002", "AquaSerpent", "Water", 110, 45);
        // system.addSpecies("GD003", "LeafBeast", "Grass", 90, 55);
        // system.addSpecies("GD004", "LeafBeast2", "Grass", 90, 55);
        // // Uncomment the line below to test duplicate species entry
        // // Duplicate species should not be added
        // // system.addSpecies("FD001", "FireDragon", "Fire", 100, 50);

        // // Add players to the system
        // system.addPlayer("8", "John");
        // system.addPlayer("P200", "Alice");
        // system.addPlayer("P300", "Bob");
        // // Uncomment the line below to test duplicate player entry
        // // Duplicate players should not be added
        // // system.addPlayer("P100", "John");

        // // Assign monsters to players
        // system.assignMonster("P100", "Blaze", "FD001");
        // system.assignMonster("P200", "Sprout", "GD003");
        // system.assignMonster("P300", "Aqua", "WD002");
        // system.assignMonster("P200", "Carot", "GD003");
        // // A battle between two players and print the result
        // System.out.println(system.battle("P100", "P200")); // Blaze vs. Sprout
        // // Uncomment below to test a different battle scenario
        // // System.out.println(system.battle("P100", "P300")); // Blaze vs. Aqua

        // // Save data
        // system.saveData();
        // System.out.println("Data saved successfully.");

        // // Clear current state
        // system = new MonsterBattleSystem();

        // // Load data
        // system.loadData();
        // System.out.println("Data loaded successfully.");

        // // Print the current state of the system (all players and their monsters)
        // System.out.println(system);
        while (true) {
            System.out.println("=======================");
            System.out.println("Monster Battle Quest");
            System.out.println("=======================");
            System.out.println("1. Add Monster Species");
            System.out.println("2. Register Player");
            System.out.println("3. Recruit Monster to Player");
            System.out.println("4. Display Player Info");
            System.out.println("5. Display All Data");
            System.out.println("6. Save Data");
            System.out.println("7. Load Data");
            System.out.println("8. Battle Between Two Players");
            System.out.println("X. Exit");
            System.out.println("Please enter an option (1-8 or X):");
            String option = scanner.nextLine().trim();

            if (option.equalsIgnoreCase("X")) {
                System.out.println("Exiting Monster Battle Quest. Goodbye!");
                break;
            }

            switch (option) {
                case "1": // Add Monster Species
                    System.out.println("Enter Species Code:");
                    String speciesCode = scanner.nextLine().trim();
                    System.out.println("Enter Species Name:");
                    String speciesName = scanner.nextLine().trim();
                    System.out.println("Enter Type (Fire/Water/Grass):");
                    String type = scanner.nextLine().trim();
                    System.out.println("Enter Monster Level:");
                    int level = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("Enter Base HP:");
                    int baseHP = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("Enter Base Attack:");
                    int baseAttack = Integer.parseInt(scanner.nextLine().trim());
                    system.addSpecies(speciesCode, speciesName, type, level, baseHP, baseAttack);
                    System.out.println("Species added successfully.");
                    break;

                case "2": // Register Player
                    System.out.println("Enter Player ID:");
                    String playerId = scanner.nextLine().trim();
                    System.out.println("Enter Player Name:");
                    String playerName = scanner.nextLine().trim();
                    system.addPlayer(playerId, playerName);
                    System.out.println("Player registered successfully.");
                    break;

                case "3": // Recruit Monster to Player
                    System.out.println("Enter Player ID:");
                    String pId = scanner.nextLine().trim();
                    System.out.println("Enter Species Code:");
                    String sCode = scanner.nextLine().trim();
                    system.assignMonster(pId, sCode);
                    System.out.println("Monster recruited successfully.");
                    break;

                case "4": // Display Player Info
                    System.out.println("Enter Player ID:");
                    String pIdToDisplay = scanner.nextLine().trim();
                    system.displayPlayerInfo(pIdToDisplay);
                    break;

                case "5": // Display All Data
                    System.out.println(system.toString());
                    break;

                case "6": // Save Data
                    system.saveData();
                    System.out.println("Data saved successfully.");
                    break;

                case "7": // Load Data
                    system.loadData();
                    System.out.println("Data loaded successfully.");
                    break;

                case "8": // Battle Between Two Players
                    System.out.println("Enter First Player ID:");
                    String player1Id = scanner.nextLine().trim();
                    System.out.println("Enter Second Player ID:");
                    String player2Id = scanner.nextLine().trim();
                    System.out.println(system.battle(player1Id, player2Id));
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1-8 or X.");
            }
        }
        scanner.close();

    }
}
