package org.example;

import java.util.Scanner;

public class GameRunner {
    // This is the main class that runs the game.

    public static void main(String[] args) {
        // This is the main method that starts the program.

        // This plays some music :)
        String filepath = "/CasinoJazz.wav";
        // A string variable 'filepath' is assigned the path of the music file to be played.

        PlayerMusic music = new PlayerMusic();
        // An instance of 'PlayerMusic' class is created to handle the music playback.

        music.playMusic(filepath);
        // The 'playMusic' method of 'PlayerMusic' is called to start playing the music using the provided file path.

        Scanner sc = new Scanner(System.in);
        // A 'Scanner' object is created to read input from the user.

        boolean playGame = true;
        // A boolean variable 'playGame' is set to true to control the main game loop.

        while (playGame) {
            // The main game loop begins and will run as long as 'playGame' is true.

            Deck deck = new Deck();
            // A new 'Deck' object is created to represent a deck of cards.

            deck.shuffle();
            // The 'shuffle' method of 'Deck' is called to randomize the order of the cards.

            Player player = new Player(100);
            // A new 'Player' object is created for the player with an initial balance of 100.

            Player dealer = new Player(0);
            // A new 'Player' object is created for the dealer with an initial balance of 0 (dealer doesn't use money).

            boolean playing = true;
            // A boolean variable 'playing' is set to true to control the loop for a single game session.

            while (playing) {
                // This loop represents a single game session and will continue as long as 'playing' is true.

                if (player.getBalance() <= 0) {
                    // Checks if the player's balance is 0 or less.

                    System.out.println("Oh no, you've run out of cash. Better luck next time!");
                    // If the player's balance is 0, a message is printed indicating that the player has no money left.

                    playing = false;
                    // The 'playing' variable is set to false to exit the game session loop.

                    break;
                    // Exits the current game session loop.
                }

                player.clearHand();
                // The player's hand of cards is cleared.

                dealer.clearHand();
                // The dealer's hand of cards is cleared.

                System.out.println("You have $" + player.getBalance());
                // Prints the player's current balance.

                System.out.print("Enter your bet (increments of 5): ");
                // Prompts the player to enter a bet amount.

                int bet = sc.nextInt();
                // Reads the bet amount entered by the player.

                while (bet % 5 != 0 || bet > player.getBalance()) {
                    // Validates the bet: ensures it is a multiple of 5 and within the player's balance.

                    if (bet > player.getBalance()) {
                        System.out.print("You don't have that much money, silly! Bet something within your price range: ");
                        // If the bet is more than the player's balance, a message is printed to ask for a lower bet.
                    } else {
                        System.out.print("Invalid bet. Enter your bet (increments of 5): ");
                        // If the bet is not a multiple of 5, a message is printed to ask for a valid bet.
                    }

                    bet = sc.nextInt();
                    // Reads the new bet amount from the player.
                }

                player.addCardToHand(deck.dealNextCard());
                // Deals the first card to the player.

                player.addCardToHand(deck.dealNextCard());
                // Deals the second card to the player.

                dealer.addCardToHand(deck.dealNextCard());
                // Deals the first card to the dealer.

                dealer.addCardToHand(deck.dealNextCard());
                // Deals the second card to the dealer.

                System.out.println("Your hand: " + player.getHand() + " (value: " + player.getHandValue() + ")");
                // Prints the player's hand and its value.

                System.out.println("Dealer's hand: [" + dealer.getHand().get(0) + ", ?]");
                // Prints the dealer's hand but only reveals the first card.

                boolean playerTurn = true;
                // A boolean variable 'playerTurn' is set to true to control the player's turn.

                boolean playerBusted = false;
                // A boolean variable 'playerBusted' is set to false to track if the player has busted (exceeded 21).

                while (playerTurn) {
                    // This loop controls the player's actions (hit or stand) during their turn.

                    if (player.getHandValue() == 21) {
                        System.out.println("You have 21!");
                        // If the player has exactly 21, a message is printed.

                        playerTurn = false;
                        // Ends the player's turn.
                        break;
                        // Exits the player's turn loop.
                    }

                    System.out.print("Hit or stand? (h/s): ");
                    // Prompts the player to choose whether to hit (draw another card) or stand (end their turn).

                    char choice = sc.next().charAt(0);
                    // Reads the player's choice ('h' for hit, 's' for stand).

                    if (choice == 'h') {
                        player.addCardToHand(deck.dealNextCard());
                        // If the player chooses to hit, a card is dealt to them.

                        System.out.println("Your hand: " + player.getHand() + " (value: " + player.getHandValue() + ")");
                        // Prints the updated player's hand and its value.

                        if (player.getHandValue() > 21) {
                            playerBusted = true;
                            // If the player's hand value exceeds 21, they are considered busted.

                            playerTurn = false;
                            // Ends the player's turn.
                        }
                    } else {
                        playerTurn = false;
                        // If the player chooses to stand, ends their turn.
                    }
                }

                if (playerBusted) {
                    System.out.println("You busted! Dealer wins.");
                    // If the player busted, a message is printed indicating that the dealer wins.

                    player.adjustBalance(-bet);
                    // The player's balance is adjusted by subtracting the bet amount.
                } else {
                    System.out.println("Dealer's hand: " + dealer.getHand() + " (value: " + dealer.getHandValue() + ")");
                    // If the player didn't bust, the dealer's full hand and its value are revealed.

                    while (dealer.getHandValue() < 17) {
                        dealer.addCardToHand(deck.dealNextCard());
                        // The dealer keeps drawing cards until their hand value is 17 or more.

                        System.out.println("Dealer's hand: " + dealer.getHand() + " (value: " + dealer.getHandValue() + ")");
                        // Prints the updated dealer's hand and its value.
                    }

                    if (dealer.getHandValue() > 21 || player.getHandValue() > dealer.getHandValue()) {
                        System.out.println("You win!");
                        // If the dealer busts or the player's hand is higher, the player wins.

                        player.adjustBalance(bet);
                        // The player's balance is adjusted by adding the bet amount.
                    } else if (player.getHandValue() < dealer.getHandValue()) {
                        System.out.println("Dealer wins.");
                        // If the dealer's hand is higher, the dealer wins.

                        player.adjustBalance(-bet);
                        // The player's balance is adjusted by subtracting the bet amount.
                    } else {
                        System.out.println("It's a tie!");
                        // If the hands are equal, it's a tie.
                    }
                }

                if (player.getBalance() > 0) {
                    System.out.print("Play again? (y/n): ");
                    // If the player has a positive balance, they are asked if they want to play again.

                    char playAgain = sc.next().charAt(0);
                    // Reads the player's choice.

                    while (playAgain != 'y' && playAgain != 'n') {
                        System.out.print("That's not a choice silly! Please choose y or n: ");
                        // Validates the input, asking again if the player didn't enter 'y' or 'n'.

                        playAgain = sc.next().charAt(0);
                        // Reads the new input.
                    }

                    if (playAgain != 'y') {
                        playing = false;
                        // If the player chooses not to play again, the game session loop ends.

                        System.out.println("Have a nice day!");
                        // Prints a goodbye message.
                    }
                }
            }

            if (player.getBalance() <= 0) {
                System.out.print("Do you want to replay the game with a new balance? (y/n): ");
                // If the player's balance is 0 or less, they are asked if they want to replay the game with a new balance.

                char replay = sc.next().charAt(0);
                // Reads the player's choice.

                while (replay != 'y' && replay != 'n') {
                    System.out.print("That's not a choice silly! Please choose y or n: ");
                    // Validates the input, asking again if the player didn't enter 'y' or 'n'.

                    replay = sc.next().charAt(0);
                    // Reads the new input.
                }

                if (replay != 'y') {
                    playGame = false;
                    // If the player chooses not to replay, the main game loop ends.

                    System.out.println("Thanks for playing! Better luck next time!");
                    // Prints a goodbye message.
                }
            } else {
                playGame = false;
                // If the player has a positive balance, the main game loop ends.
            }
        }
    }
}
