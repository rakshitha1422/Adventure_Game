package com.adventure;

public class Main {
    public static void main(String[] args) {

        AdventureGame. setupGame();
        AdventureGame.playGame();
    }

}
/*Win Scenario
    ->Initially in entrance room
    ->go north(You go to forest)
    ->talk(To collect the key)
    ->Check Inventory(To whether key is collected or not if not go and collect it first)
    ->go south>go esat>go east
    ->Win...!
 */
