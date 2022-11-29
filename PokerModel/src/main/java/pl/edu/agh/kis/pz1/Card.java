package pl.edu.agh.kis.pz1;

import java.util.Random;

/**
 * Class Card is representation of a single Card
 * It has two attribute rank and suit
 * Plenty of methods help to make many operations on a Card as print @see #hashCode and much more.
 */
public class Card {

    protected Rank rank;
    protected Suit suit;

    public Card(Rank cRank, Suit cSuit) {
        rank = cRank;
        suit = cSuit;
    }

    public static Rank getRank(Card card)
    {
        return card.rank;
    }

    public static Suit getSuit(Card card)
    {
        return card.suit;
    }

    /**
     * HashCode used to sort the Deck
     */
    @Override
    public int hashCode() {
        return rank.ordinal() + suit.ordinal() * 10;
    }

    /**
     * HashCode used to shuffle the Deck
     */
    public int hashCode2() {
        Random rand = new Random();
        double x = rand.nextInt(5);
        double y = rand.nextInt(10);
        return (int) ((Math.pow(rank.ordinal(), x) + 1) * 13 + suit.ordinal() % y / 3);
    }

    void print() {
        System.out.print(this.rank);
        System.out.println(this.suit);
    }

    /**
     * Methods checking if two cards are equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }
}
