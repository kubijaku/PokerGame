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

    public int containSameSuit( Card checkCard, Stack<Card> newcards) {
        for( Card card : newcards)
        {
            if ( Card.getSuit( checkCard ) == Card.getSuit( card )) return newcards.indexOf(card);
        }
        return -1;
    }

    public void checkVariats(Stack<Card> cards)
    {
        Stack<Card> newCards = new Stack<Card>();
        if(sameColours(cards)) {
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
        } else {
            newCards = cards;
            newCards = sortCards(newCards);
            Suit suit0 = Card.getSuit(newCards.pop());
            if (Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()]) playerVariants.push(Variants.QUADS);
            else {
                newCards = cards;
                newCards = sortCards(newCards);
                newCards.pop();
                Suit suit1 = Card.getSuit(newCards.pop());
                if (Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()] &&
                        Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()] &&
                        Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()])
                    playerVariants.push(Variants.QUADS);
                else {
                    newCards = cards;
                    newCards = sortCards(newCards);
                    for (Card cardToBeChecked : newCards) {
                        if (containSameSuit(cardToBeChecked, newCards) != -1) {
                            newCards.remove(containSameSuit(cardToBeChecked, newCards));

                            for (Card cardToBeChecked2 : newCards) {
                                if (containSameSuit(cardToBeChecked2, newCards) != -1) {
                                    newCards.remove(containSameSuit(cardToBeChecked2, newCards));
                                    newCards.remove(cardToBeChecked2);

                                    for (Card cardToBeChecked3 : newCards) {
                                        if (containSameSuit(cardToBeChecked3, newCards) != -1)
                                            playerVariants.push(Variants.FULL_HOUSE);
                                        else playerVariants.push(Variants.THREE_OF_KIND);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        newCards = cards;
        newCards = sortCards(newCards);
        int numberOfPairs = 0;
        for (Card cardToBeChecked : newCards) {
            if (containSameSuit(cardToBeChecked, newCards) != -1) {
                newCards.remove(cardToBeChecked);
                newCards.remove(containSameSuit(cardToBeChecked, newCards));
                numberOfPairs = numberOfPairs + 1;
            }
        }
        if (numberOfPairs == 2) playerVariants.push(Variants.TWO_PAIR);
        if (numberOfPairs == 1) playerVariants.push(Variants.ONE_PAIR);

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

    public Stack<Card> sortCards(Stack<Card> newcards ) {
        Stack<Card> tmpStack = new Stack<Card>();
        while (!newcards.isEmpty()) {
            // pop out the first element
            Card tmp = newcards.pop();

            // while temporary stack is not empty and
            // top of stack is greater than temp
            while (!tmpStack.isEmpty() && tmpStack.peek().hashCode() > tmp.hashCode()) {
                // pop from temporary stack and
                // push it to the input stack
                newcards.push(tmpStack.pop());
            }

            // push temp in temporary of stack
            tmpStack.push(tmp);
        }
        newcards = tmpStack;

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
