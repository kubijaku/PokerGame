package pl.edu.agh.kis.pz1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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
    private Stack<Variants> playerVariants = new Stack<Variants>();
    private Variants[] highestVariant = new Variants[1]; ;


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
    public Stack<Card> get5Cards(Deck deck) {
        Stack<Card> newplayerCards = new Stack<Card>();
        for (int i = 0; i < 5; i++) {
            newplayerCards.push(deck.Cards.pop());
        }
        playerCards = newplayerCards;
        return newplayerCards;
    }

    public boolean contain( Card card)
    {
        for( Card card1 : playerCards)
        {
            if ( card.equals(card1))  return true;
        }
        return false;
    }

    public int containSameSuit( Card checkCard, List<Card> newCards1) {
        for( Card card : newCards1)
        {
            if ( Card.getSuit( checkCard ) == Card.getSuit( card ) && checkCard != card) return newCards1.indexOf(card);
        }
        return -1;
    }

    public Stack<Variants> checkAllPossibleVariants(Stack<Card> cards)
    {
        if(sameColours(cards)) {
            Stack<Card> newCards = (Stack<Card>) cards.clone();
            newCards = sortCards(newCards);
            List<Card> newCardsList = (List<Card>) newCards;
            if(Card.getSuit(newCardsList.get(0)) == Suit._10 &&
                    Card.getSuit(newCardsList.get(1)) == Suit._WALET &&
                    Card.getSuit(newCardsList.get(2)) == Suit._DAMA &&
                    Card.getSuit(newCardsList.get(3)) == Suit._KROL &&
                    Card.getSuit(newCardsList.get(4)) == Suit._AS ) playerVariants.push(Variants.ROYAL_FLUSH);
            else newCards = (Stack<Card>) cards.clone();
            Suit suit0 = Card.getSuit(newCards.pop());
            if (suit0 != Suit._10 &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()+1] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()+1] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()+1] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()+1] ) playerVariants.push(Variants.STRAIGHT_FLUSH);
            else playerVariants.push(Variants.FLUSH);
        } else {
            Stack<Card> newCards = (Stack<Card>) cards.clone();
            newCards = sortCards(newCards);
            Suit suit0 = Card.getSuit(newCards.pop());
            if (Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()] &&
                    Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()]) playerVariants.push(Variants.QUADS);
            else {
                newCards = (Stack<Card>) cards.clone();
                newCards = sortCards(newCards);
                newCards.pop();
                Suit suit1 = Card.getSuit(newCards.pop());
                if (Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()] &&
                        Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()] &&
                        Card.getSuit(newCards.pop()) == Suit.values()[suit0.ordinal()])
                    playerVariants.push(Variants.QUADS);
                else {
                    newCards = (Stack<Card>) cards.clone();
                    newCards = sortCards(newCards);
                    List<Card> newCardsList = (List<Card>) newCards;
                    for (int i=0; i<newCardsList.size();i++ ) {
                        if (containSameSuit(newCardsList.get(i), newCardsList) != -1) {
                            newCardsList.remove(containSameSuit(newCardsList.get(i), newCardsList));

                            if (containSameSuit(newCardsList.get(i), newCardsList) != -1) {
                                newCardsList.remove(containSameSuit(newCardsList.get(i), newCardsList));
                                newCardsList.remove(newCardsList.get(i));
                                if (newCardsList.size() > 1 && containSameSuit(newCardsList.get(0), newCardsList) != -1) {
                                    newCardsList.remove(containSameSuit(newCardsList.get(0), newCardsList));

                                    playerVariants.push(Variants.FULL_HOUSE);
                                    break;
                                }
                                playerVariants.push(Variants.THREE_OF_KIND);
                                break;
                            }
                        }
                    }
                }
            }
        }
        Stack<Card> newCards = new Stack<Card>();
        newCards = (Stack<Card>) cards.clone();
        newCards = sortCards(newCards);
        int numberOfPairs = 0;
        List<Card> newCardsList = (List<Card>) newCards.clone();
        for (int k =0; k< newCardsList.size(); k++) {
            newCards = (Stack<Card>) cards.clone();
            newCards = sortCards(newCards);
            newCards.remove(newCardsList.get(k));
            if (containSameSuit(newCardsList.get(k), newCards) != -1) {
                newCards.remove(containSameSuit(newCardsList.get(k), newCards));
                newCards.remove(newCardsList.get(k));
                numberOfPairs = numberOfPairs + 1;
                k = k + 2;
            }
        }
        if (numberOfPairs == 2) playerVariants.push(Variants.TWO_PAIR);
        if (numberOfPairs == 1) playerVariants.push(Variants.ONE_PAIR);
        return playerVariants;
    }

    public Variants HIGHestVariant ( Stack<Card> cards) {
        Stack<Variants> allPossibleVariants = new Stack<Variants>();
        allPossibleVariants = checkAllPossibleVariants( cards );
        Variants[] allPossibleVariantsArray = new Variants[allPossibleVariants.size()];
        int i = 0;
        for ( Variants actualVariant : allPossibleVariants )
        {
            allPossibleVariantsArray[i] = actualVariant;
            i++;
        }
        for( int j=0; j<i-2; j++)
        {
            if( allPossibleVariantsArray[j].ordinal() < allPossibleVariantsArray[j+1].ordinal() )
            {
                Variants pom = allPossibleVariantsArray[j];
                allPossibleVariantsArray[j] = allPossibleVariantsArray[j+1];
                allPossibleVariantsArray[j+1] = pom;
            }
        }
        if(allPossibleVariants.size() > 0 )
        {
            highestVariant[0] = allPossibleVariantsArray[0];
            return allPossibleVariantsArray[0];
        }
        else return Variants.HIGH_CARD;


    }

    public Stack<Variants> getPlayerVariants() {
        return playerVariants;
    }


    public boolean sameColours(Stack<Card> cards)
    {
        Stack<Card> tempPlayerCards = (Stack<Card>) cards.clone();
        Rank colour = Card.getRank(tempPlayerCards.pop());

        while(!tempPlayerCards.isEmpty())
        {
            if ( Card.getRank(tempPlayerCards.pop())!=colour)  return false;
        }
        return false;
    }

    public Stack<Card> sortCards(Stack<Card> newcards1 ) {
        Stack<Card> tmpStack = new Stack<Card>();
        Stack<Card> newcards = (Stack<Card>) newcards1.clone();
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


    public Stack<Card> changeCard(int i, Stack<Card> playerCards, Deck deck) {
        Stack<Card> newPlayerCards = (Stack<Card>) playerCards.clone();
        newPlayerCards.remove(i);
        newPlayerCards.push(deck.Cards.pop());
        playerCards = newPlayerCards;
        return newPlayerCards;
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
