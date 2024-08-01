package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements DeckActions {
    private List<Card> myCards;
    private int numCards;

    public Deck() {
        myCards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                myCards.add(new Card(suit, ranks[i], values[i]));
            }
        }
        this.numCards = myCards.size();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(myCards);
    }

    @Override
    public Card dealNextCard() {
        if (myCards.isEmpty()) {
            return null;
        }
        return myCards.remove(0);
    }

    @Override
    public void printDeck(int numToPrint) {
        for (int i = 0; i < numToPrint && i < myCards.size(); i++) {
            System.out.println(myCards.get(i));
        }
    }
}
