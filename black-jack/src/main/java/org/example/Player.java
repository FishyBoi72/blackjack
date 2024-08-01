package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private int balance;

    public Player(int initialBalance) {
        this.hand = new ArrayList<>();
        this.balance = initialBalance;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHandValue() {
        int value = 0;
        int numAces = 0;

        for (Card card : hand) {
            value += card.getValue();
            if (card.getRank().equals("Ace")) {
                numAces++;
            }
        }

        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }

    public int getBalance() {
        return balance;
    }

    public void adjustBalance(int amount) {
        balance += amount;
    }
}
