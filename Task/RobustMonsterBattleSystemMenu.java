import java.util.Scanner;

public class RobustMonsterBattleSystemMenu {
    private MonsterBattleSystem system;
    private Scanner scanner;

    public RobustMonsterBattleSystemMenu() {
        this.system = new MonsterBattleSystem();
        this.scanner = new Scanner(System.in);
    }

    public void runMenu() {
        while (true) {
            System.out.println("==============================================");
            System.out.println("Monster Battle Quest");
            System.out.println("==============================================");
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

            try {
                switch (option) {
                    case "1": // Add Monster Species
                        System.out.println("Enter Species Code:");
                        String speciesCode = scanner.nextLine().trim();
                        System.out.println("Enter Species Name:");
                        String speciesName = scanner.nextLine().trim();
                        System.out.println("Enter Type (Fire/Water/Grass/Thunder):");
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
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Returning to main menu...");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        RobustMonsterBattleSystemMenu menu = new RobustMonsterBattleSystemMenu();
        menu.runMenu();
    }
}