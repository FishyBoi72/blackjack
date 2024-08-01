package org.example;

import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        // This plays some music :)
        String filepath = "/CasinoJazz.wav";
        PlayerMusic music = new PlayerMusic();
        music.playMusic(filepath);

        Scanner sc = new Scanner(System.in);

        boolean playGame = true;
        while (playGame) {
            Deck deck = new Deck();
            deck.shuffle();

            Player player = new Player(100);
            Player dealer = new Player(0);

            boolean playing = true;

            while (playing) {
                if (player.getBalance() <= 0) {
                    System.out.println("Oh no, you've run out of cash. Better luck next time!");
                    playing = false;
                    break;
                }

                player.clearHand();
                dealer.clearHand();

                System.out.println("You have $" + player.getBalance());
                System.out.print("Enter your bet (increments of 5): ");
                int bet = sc.nextInt();
                while (bet % 5 != 0 || bet > player.getBalance()) {
                    if (bet > player.getBalance()) {
                        System.out.print("You don't have that much money, silly! Bet something within your price range: ");
                    } else {
                        System.out.print("Invalid bet. Enter your bet (increments of 5): ");
                    }
                    bet = sc.nextInt();
                }

                player.addCardToHand(deck.dealNextCard());
                player.addCardToHand(deck.dealNextCard());
                dealer.addCardToHand(deck.dealNextCard());
                dealer.addCardToHand(deck.dealNextCard());

                System.out.println("Your hand: " + player.getHand() + " (value: " + player.getHandValue() + ")");
                System.out.println("Dealer's hand: [" + dealer.getHand().get(0) + ", ?]");

                boolean playerTurn = true;
                boolean playerBusted = false;
                while (playerTurn) {
                    if (player.getHandValue() == 21) {
                        System.out.println("You have 21!");
                        playerTurn = false;
                        break;
                    }

                    System.out.print("Hit or stand? (h/s): ");
                    char choice = sc.next().charAt(0);
                    if (choice == 'h') {
                        player.addCardToHand(deck.dealNextCard());
                        System.out.println("Your hand: " + player.getHand() + " (value: " + player.getHandValue() + ")");
                        if (player.getHandValue() > 21) {
                            playerBusted = true;
                            playerTurn = false;
                        }
                    } else {
                        playerTurn = false;
                    }
                }

                if (playerBusted) {
                    System.out.println("You busted! Dealer wins.");
                    player.adjustBalance(-bet);
                } else {
                    System.out.println("Dealer's hand: " + dealer.getHand() + " (value: " + dealer.getHandValue() + ")");
                    while (dealer.getHandValue() < 17) {
                        dealer.addCardToHand(deck.dealNextCard());
                        System.out.println("Dealer's hand: " + dealer.getHand() + " (value: " + dealer.getHandValue() + ")");
                    }

                    if (dealer.getHandValue() > 21 || player.getHandValue() > dealer.getHandValue()) {
                        System.out.println("You win!");
                        player.adjustBalance(bet);
                    } else if (player.getHandValue() < dealer.getHandValue()) {
                        System.out.println("Dealer wins.");
                        player.adjustBalance(-bet);
                    } else {
                        System.out.println("It's a tie!");
                    }
                }

                if (player.getBalance() > 0) {
                    System.out.print("Play again? (y/n): ");
                    char playAgain = sc.next().charAt(0);
                    while (playAgain != 'y' && playAgain != 'n') {
                        System.out.print("That's not a choice silly! Please choose y or n: ");
                        playAgain = sc.next().charAt(0);
                    }
                    if (playAgain != 'y') {
                        playing = false;
                        System.out.println("Have a nice day!");
                    }
                }
            }

            if (player.getBalance() <= 0) {
                System.out.print("Do you want to replay the game with a new balance? (y/n): ");
                char replay = sc.next().charAt(0);
                while (replay != 'y' && replay != 'n') {
                    System.out.print("That's not a choice silly! Please choose y or n: ");
                    replay = sc.next().charAt(0);
                }
                if (replay != 'y') {
                    playGame = false;
                    System.out.println("Thanks for playing! Better luck next time!");
                }
            } else {
                playGame = false;
            }
        }
    }
}