package pl.edu.agh.kis.pz1;
import pl.edu.agh.kis.pz1.Card;

import java.util.Stack;

/**
 * Class Deck is representation of Deck of Cards
 * It has one attribute Cards
 * Methods:
 * {@link #fulfillDeck() fullfillDeck()}
 * {@link #factory()} () factory()}
 * {@link #shuffle() shuffle()}
 * {@link #print() print()}
 * {@link #give5Cards(Player pl) give5Cards(Player pl)}
 * {@link #cardInDeck(Card card) cardInDeck()}
 */
public class Deck {

    protected Stack<Card> Cards = new Stack<>();

    /**
     * Method fulfill Deck
     * Automatically  checks wheter card is already in deck or not
     */
    void fulfillDeck()
    {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card newcard = new Card(rank, suit);
                if (!this.cardInDeck(newcard))
                    Cards.push(newcard);

            }
        }
    }

    /**
     * Method use method {@link #fulfillDeck() fullfillDeck()} to insert lacking cards to {@link #Cards Cards}
     * Sort {@link #hashCode() hashCode()} using {@link #hashCode() hashCode()}
     * @return Stack of {@link pl.edu.agh.kis.pz1.Card}
     */
    public Stack<Card> factory() {
        Stack<Card> tmpStack = new Stack<Card>();
        if (Cards.isEmpty())
            fulfillDeck();
        while (!Cards.isEmpty()) {
            // pop out the first element
            Card tmp = Cards.pop();

            // while temporary stack is not empty and
            // top of stack is greater than temp
            while (!tmpStack.isEmpty() && tmpStack.peek().hashCode() > tmp.hashCode()) {
                // pop from temporary stack and
                // push it to the input stack
                Cards.push(tmpStack.pop());
            }

            // push temp in temporary of stack
            tmpStack.push(tmp);
        }

        Cards = tmpStack;
        System.out.println("Sorted cards");
        this.print();
        return tmpStack;
    }

    /**
     * Method use method {@link #fulfillDeck() fullfillDeck()} to insert cards to {@link #Cards Cards} if Deck is empty
     * Sort {@link #Cards Cards} using {@link Card#hashCode2() hashCode2()} to shuffle Deck of cards
     * @return Stack of {@link pl.edu.agh.kis.pz1.Card}
     */
    public Stack<Card> shuffle() {
        Stack<Card> tmpStack = new Stack<Card>();
        if (Cards.isEmpty())
            fulfillDeck();
        while (!Cards.isEmpty()) {
            Card newcard = Cards.pop();

            while (!tmpStack.isEmpty() && tmpStack.peek().hashCode2() > newcard.hashCode()) {
                Cards.push(tmpStack.pop());
            }
            tmpStack.push(newcard);

        }
        Cards = tmpStack;
        return tmpStack;
    }

    /**
     * Method Print Deck of cards to screen
     */
    public void print() {
        System.out.println();
        System.out.println();
        for (Card card : this.Cards) {
            card.print();
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Method 'gives' 5 {@link Card cards} from c to Player - {@link Player#playerCards playerCards}
     * @param  pl  object representing PLayer
     */
    public void give5Cards(Player pl) {
        for (int i = 0; i < 5; i++) {
            pl.playerCards.push(this.Cards.pop());
        }
    }

    /**
     * Method 'checks' whether the card is in the {@link #Cards Deck} or not
     * @param  card  object representing specific Card to we want to check
     * @return True or False
     */
    public boolean cardInDeck(Card card) {
        for (Card card1 : Cards) {
            if (card1.rank.equals(card.rank) && card1.suit.equals(card.suit))
                return true;
        }
        return false;
    }

}
