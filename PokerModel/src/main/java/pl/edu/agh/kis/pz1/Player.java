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
    private Stack<Card> playerCards = new Stack<Card>();
    private Stack<Variants> playerVariants = new Stack<Variants>();


    /**
     * Method Print {@link #playerCards}
     */
    public void showCards() {
        for (Card card : playerCards) {
            card.print();
        }
    }


    /**
     * Gives 5 {@link Card cards} to  {@link #playerCards}
     */
    public void get5Cards(Deck deck) {
        for (int i = 0; i < 5; i++) {
            playerCards.push(deck.Cards.pop());
        }
    }

    public boolean contain( Card card)
    {
        for( Card card1 : playerCards)
        {
            if ( card.equals(card1))  return true;
        }
        return false;
    }

    public void checkVariats(Stack<Card> cards)
    {
        if(sameColours(cards)) {
            Stack<Card> newCards = new Stack<Card>();
            newCards = cards;
            if(Card.getSuit(newCards.pop()) == Suit._10 &&
                    Card.getSuit(newCards.pop()) == Suit._WALET &&
                    Card.getSuit(newCards.pop()) == Suit._DAMA &&
                    Card.getSuit(newCards.pop()) == Suit._KROL &&
                    Card.getSuit(newCards.pop()) == Suit._AS ) playerVariants.push(Variants.ROYAL_FLUSH);
            else newCards = cards;
            Suit suit0 = Card.getSuit(newCards.pop());
            if (suit0 != Suit._10 &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()+1] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()+1] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()+1] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()+1] ) playerVariants.push(Variants.STRAIGHT_FLUSH);
            else playerVariants.push(Variants.FLUSH);
        } //else TODO

        {

        }
    }
    public boolean sameColours( Stack<Card> cards)
    {
        Rank colour = Card.getRank(cards.pop());
        for( Card card1 : playerCards)
        {
            if ( Card.getRank(cards.pop())!=colour)  return false;
        }
        return false;
    }

    public Stack<Card> sortPlayerCards() {
        Stack<Card> tmpStack = new Stack<Card>();
        while (!playerCards.isEmpty()) {
            // pop out the first element
            Card tmp = playerCards.pop();

            // while temporary stack is not empty and
            // top of stack is greater than temp
            while (!tmpStack.isEmpty() && tmpStack.peek().hashCode() > tmp.hashCode()) {
                // pop from temporary stack and
                // push it to the input stack
                playerCards.push(tmpStack.pop());
            }

            // push temp in temporary of stack
            tmpStack.push(tmp);
        }
        playerCards = tmpStack;

        return tmpStack;
    }


}

enum Variants
{
    ROYAL_FLUSH,
    STRAIGHT_FLUSH,
    QUADS,
    FULL_HOUSE,
    FLUSH,
    STRAIGHT,
    THREE_OF_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD
}
