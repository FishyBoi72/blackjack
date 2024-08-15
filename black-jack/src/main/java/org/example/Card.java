package org.example;

public class Card { // Declares the Card class, which will represent a playing card
    private String suit; // Private instance variable to hold the suit of the card (e.g., Hearts, Spades)
    private String rank; // Private instance variable to hold the rank of the card (e.g., Ace, King, 2)
    private int value; // Private instance variable to hold the value of the card (useful for game logic)

    // Constructor to initialize a new Card object with a specified suit, rank, and value
    public Card(String suit, String rank, int value) {
        this.suit = suit; // Assigns the suit parameter to the suit instance variable
        this.rank = rank; // Assigns the rank parameter to the rank instance variable
        this.value = value; // Assigns the value parameter to the value instance variable
    }

    // Public method to retrieve the suit of the card
    public String getSuit() {
        return suit; // Returns the suit of the card
    }

    // Public method to retrieve the rank of the card
    public String getRank() {
        return rank; // Returns the rank of the card
    }

    // Public method to retrieve the value of the card
    public int getValue() {
        return value; // Returns the value of the card
    }

    // Override of the toString method to provide a string representation of the card
    @Override
    public String toString() {
        return rank + " of " + suit; // Returns a string in the format "Rank of Suit" (e.g., "Ace of Spades")
    }
}
