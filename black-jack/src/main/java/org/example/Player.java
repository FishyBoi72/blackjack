package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player { // Defines a public class named Player.
    private List<Card> hand; // Declares a private List of Card objects, representing the player's hand.
    private int balance;     // Declares a private integer variable to store the player's balance.

    public Player(int initialBalance) { // Constructor for the Player class, taking an initial balance as a parameter.
        this.hand = new ArrayList<>();  // Initializes the hand as an empty ArrayList of Card objects.
        this.balance = initialBalance;  // Sets the player's balance to the initial balance provided.
    }

    public void addCardToHand(Card card) { // Method to add a Card to the player's hand.
        hand.add(card);                    // Adds the given card to the hand.
    }

    public void clearHand() { // Method to clear the player's hand.
        hand.clear();         // Removes all cards from the hand.
    }

    public List<Card> getHand() { // Method to get the player's hand.
        return hand;              // Returns the list of cards in the hand.
    }

    public int getHandValue() { // Method to calculate the total value of the cards in the player's hand.
        int value = 0;          // Initializes a variable to store the total value of the hand.
        int numAces = 0;        // Initializes a variable to count the number of Aces in the hand.

        for (Card card : hand) {          // Loops through each card in the hand.
            value += card.getValue();     // Adds the card's value to the total value.
            if (card.getRank().equals("Ace")) { // Checks if the card is an Ace.
                numAces++;                 // Increments the number of Aces.
            }
        }

        while (value > 21 && numAces > 0) { // Adjusts the value if the total exceeds 21 and there are Aces in the hand.
            value -= 10;                   // Reduces the total value by 10 for each Ace.
            numAces--;                     // Decreases the number of Aces.
        }

        return value; // Returns the calculated total value of the hand.
    }

    public int getBalance() { // Method to get the player's current balance.
        return balance;       // Returns the balance.
    }

    public void adjustBalance(int amount) { // Method to adjust the player's balance by a specified amount.
        balance += amount;                   // Increases or decreases the balance by the given amount.
    }
}
