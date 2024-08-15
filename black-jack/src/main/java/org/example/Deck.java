package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements DeckActions {  // Defines a class named 'Deck' that implements the 'DeckActions' interface.
    private List<Card> myCards;  // Declares a private list of 'Card' objects to represent the cards in the deck.
    private int numCards;  // Declares a private integer to track the number of cards in the deck.

    public Deck() {  // Constructor for the 'Deck' class.
        myCards = new ArrayList<>();  // Initializes 'myCards' as an empty ArrayList.
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};  // Creates an array of the four suits in a standard deck of cards.
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};  // Creates an array of the ranks in a standard deck of cards.
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};  // Creates an array of integer values corresponding to each rank.

        for (String suit : suits) {  // Iterates over each suit in the 'suits' array.
            for (int i = 0; i < ranks.length; i++) {  // Iterates over each rank in the 'ranks' array.
                myCards.add(new Card(suit, ranks[i], values[i]));  // Creates a new 'Card' object with the current suit, rank, and value, and adds it to the 'myCards' list.
            }
        }
        this.numCards = myCards.size();  // Sets 'numCards' to the number of cards currently in the 'myCards' list.
    }

    @Override
    public void shuffle() {  // Overrides the 'shuffle' method from the 'DeckActions' interface.
        Collections.shuffle(myCards);  // Shuffles the 'myCards' list to randomize the order of the cards.
    }

    @Override
    public Card dealNextCard() {  // Overrides the 'dealNextCard' method from the 'DeckActions' interface.
        if (myCards.isEmpty()) {  // Checks if the 'myCards' list is empty.
            return null;  // Returns 'null' if there are no more cards to deal.
        }
        return myCards.remove(0);  // Removes and returns the first card in the 'myCards' list.
    }

    @Override
    public void printDeck(int numToPrint) {  // Overrides the 'printDeck' method from the 'DeckActions' interface.
        for (int i = 0; i < numToPrint && i < myCards.size(); i++) {  // Iterates over the first 'numToPrint' cards or the total number of cards, whichever is smaller.
            System.out.println(myCards.get(i));  // Prints the current card to the console.
        }
    }
}
