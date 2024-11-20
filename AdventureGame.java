package com.adventure;

import java.util.*;

public class AdventureGame {
    static Map<String, String> roomDescriptions = new HashMap<>();
    static Map<String, Map<String, String>> roomExits = new HashMap<>();

    // Player attributes
    static int playerHealth = 100;
    static List<String> inventory = new ArrayList<>();
    static String currentRoom = "Entrance";

    static void setupGame() {
        // Setting up room descriptions
        roomDescriptions.put("Entrance", "You are at the entrance of a mysterious dungeon.");
        roomDescriptions.put("Forest", "A dense forest surrounds you. There's a faint path leading east.");
        roomDescriptions.put("Dungeon", "A dark and damp dungeon. You feel a presence here.");
        roomDescriptions.put("Treasure Room", "The room glows with the shine of gold. The treasure is here!");

        // Setting up room exits
        roomExits.put("Entrance", Map.of("north", "Forest", "east", "Dungeon"));
        roomExits.put("Forest", Map.of("south", "Entrance"));
        roomExits.put("Dungeon", Map.of("west", "Entrance", "east", "Treasure Room"));
        roomExits.put("Treasure Room", Map.of("west", "Dungeon"));
    }

    static void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Adventure Game!");

        while (true) {
            System.out.println("\n" + roomDescriptions.get(currentRoom));
            System.out.println("Exits: " + roomExits.get(currentRoom).keySet());
            System.out.print("> ");
            //enter command what to do
            String command = scanner.nextLine().toLowerCase();

            if (command.startsWith("go ")) {
                move(command.substring(3));
            } else {
                switch (command) {
                    case "check inventory":
                        checkInventory();
                        break;
                    case "talk":
                        interactWithNPC();
                        break;
                    case "attack":
                        attackEnemy();
                        break;
                    case "run":
                        runFromEnemy();
                        break;
                    case "use potion":
                        usePotion();
                        break;
                    default:
                        System.out.println("Unknown command. Try again.");
                }
            }

            if (playerHealth <= 0) {
                System.out.println("Game Over! You have been defeated.");
                break;
            }
        }

        scanner.close();
    }
    //logic for moving in the game
    static void move(String direction) {
        Map<String, String> exits = roomExits.get(currentRoom);
        if (exits.containsKey(direction)) {
            currentRoom = exits.get(direction);
            if (currentRoom.equals("Treasure Room")) {
                if(inventory.contains("Key")){
                    System.out.println("Key used to unlock the treasure room");
                    useKey();
                    System.out.println("You have found the treasure. You Win.....!!!!");
                    System.exit(0);
                }
                else{
                    System.out.println("Get the Key to Enter the treasure room");
                }

            }
        }
        else {
            System.out.println("You can't go that way.");
        }
    }

    //logic to check inventory
    static void checkInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        }
        else {
            System.out.println("Inventory: " + inventory);
        }
    }

    //logic to interact and collect inventories
    static void interactWithNPC() {
        if (currentRoom.equals("Forest")) {
            System.out.println("An old man gives you a key: 'use the key to open the treasure room and win the game'");
            inventory.add("Key");
        } else if (currentRoom.equals("Dungeon")) {
            System.out.println("A goblin offers you a potion. You take it.");
            inventory.add("potion");
        }
        else {
            System.out.println("There's no one to talk to here.");
        }
    }

    //logic to attack
    static void attackEnemy() {
        if (currentRoom.equals("Dungeon")) {
            System.out.println("You attack the goblin!");
            System.out.println("The goblin retaliates, dealing 20 damage.");
            playerHealth -= 20;
            System.out.println("Your health: " + playerHealth);
        }
        else {
            System.out.println("There's nothing to attack here.");
        }
    }

    //logic to run
    static void runFromEnemy() {
        if (currentRoom.equals("Dungeon")) {
            System.out.println("You run back to the Entrance!");
            currentRoom = "Entrance";
        }
        else {
            System.out.println("There's nothing to run from.");
        }
    }

    //logic to use potion and add health points
    static void usePotion() {
        if (inventory.contains("potion")) {
            System.out.println("You use the potion and restore 30 health.");
            playerHealth = Math.min(100, playerHealth + 30);
            inventory.remove("potion");
            System.out.println("Your health: " + playerHealth);
        }
        else {
            System.out.println("You don't have a potion.");
        }
    }

    //logic to use Key to Treasure Room
    static void useKey(){
        inventory.remove("Key");
    }
}
