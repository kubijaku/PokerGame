package pl.edu.agh.kis.pz1;

import java.util.Stack;

/**
 * Class Player is representation of Player in poker game
 * It has one attribute {@link #playerCards playerCards}
 * Methods:
 * {@link #showCards() showCards()}
 * {@link #get5Cards(Deck deck) get5Cards(Deck deck)}
 */
public class Player {
    public Stack<Card> playerCards = new Stack<Card>();

    /**
     * Method Print {@link #playerCards}
     */
    void showCards() {
        for (Card card : playerCards) {
            card.print();
        }
    }


    /**
     * Gives 5 {@link Card cards} to  {@link #playerCards}
     */
    void get5Cards(Deck deck) {
        for (int i = 0; i < 5; i++) {
            playerCards.push(deck.Cards.pop());
        }
    }

}
