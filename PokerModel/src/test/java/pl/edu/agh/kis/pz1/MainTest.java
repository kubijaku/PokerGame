package pl.edu.agh.kis.pz1;

import org.junit.Test;

import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;


public class MainTest
{
    @Test
    public void CreatingDeckCardNumber()
    {
        Deck expected = new Deck();
        expected.factory();
        int deckSize = 0;
        for (Card card : expected.Cards)
        {
            deckSize = deckSize+1;
        }
        assertEquals(52,deckSize);
    }
    @Test
    public void ShufflingDeckCardNumber()
    {
        Deck expected = new Deck();
        expected.Cards = expected.shuffle();
        int deckSize = 0;
        for (Card card : expected.Cards)
        {
            deckSize = deckSize+1;
        }
        assertEquals(52,deckSize);
    }

    @Test
    public void ShufflingDeckShuffle()
    {
        Deck expected = new Deck();
        Stack<Card> afterFactory = new Stack<Card>();
        Stack<Card> afterShuffle = new Stack<Card>();
        afterShuffle = expected.shuffle();
        afterFactory = expected.factory();

        assertNotEquals(afterShuffle,afterFactory);
    }

    @Test
    public void FullfillingDeckCardNumber()
    {
        Deck expected = new Deck();
        expected.fulfillDeck();
        int deckSize = 0;
        for (Card card : expected.Cards)
        {
            deckSize = deckSize+1;
        }
        assertEquals(52,deckSize);
    }

    @Test
    public void Giving5Cards()
    {
        Deck deck = new Deck();
        deck.fulfillDeck();
        Player pl = new Player();
        deck.give5Cards(pl);
        int deckSize = 0;
        for (Card card : pl.playerCards)
        {
            deckSize = deckSize+1;
        }
        assertEquals(5,deckSize);
    }
    @Test
    public void CheckingCardEqualsSameRefference()
    {
        Card card1 = new Card(Rank.KARO, Suit._2);
        Card card2 = card1;
        assertEquals(card2,card1);
    }
    @Test
    public void CheckingCardEqualsSameCard()
    {
        Card card1 = new Card(Rank.KARO, Suit._2);
        Card card2 = new Card(Rank.KARO, Suit._2);
        assertEquals(card2,card1);
    }

    @Test
    public void PlayersCardContain()
    {
        Card card1 = new Card(Rank.KARO, Suit._2);
        Player pl = new Player();
        pl.playerCards.push(card1);
        assertTrue(pl.contain(card1));
    }

    @Test
    public void PlayersCardNotContain()
    {
        Card card1 = new Card(Rank.KARO, Suit._2);
        Player pl = new Player();
        assertFalse(pl.contain(card1));
    }

    @Test
    public void PlayersCardContainSameSuit()
    {
        Card card1 = new Card(Rank.KARO, Suit._2);
        Card card2 = new Card(Rank.PIK, Suit._2);
        Player pl = new Player();
        pl.playerCards.push(card1);
        pl.playerCards.push(card2);
        assertEquals(1, pl.containSameSuit(card1, (List<Card>) pl.playerCards));
    }
    @Test
    public void PlayersCardNotContainSameSuit()
    {
        Card card1 = new Card(Rank.KARO, Suit._2);
        Card card2 = new Card(Rank.PIK, Suit._2);
        Player pl = new Player();
        assertEquals(-1, pl.containSameSuit(card1, (List<Card>) pl.playerCards));
    }

    @Test
    public void PlayersHighestVariantTwoPair()
    {
        Card card1 = new Card(Rank.KARO, Suit._10);
        Card card2 = new Card(Rank.KIER, Suit._10);
        Card card3 = new Card(Rank.KIER, Suit._WALET);
        Card card4 = new Card(Rank.TREFL, Suit._WALET);
        Card card5 = new Card(Rank.KARO, Suit._AS);
        Player pl = new Player();
        pl.playerCards.push(card1);
        pl.playerCards.push(card2);
        pl.playerCards.push(card3);
        pl.playerCards.push(card4);
        pl.playerCards.push(card5);
        assertEquals(Variants.TWO_PAIR, pl.HIGHestVariant(pl.playerCards));
    }

    @Test
    public void PlayersHighestVariantThreeOfKind()
    {
        Card card1 = new Card(Rank.KARO, Suit._10);
        Card card2 = new Card(Rank.KIER, Suit._10);
        Card card3 = new Card(Rank.KIER, Suit._WALET);
        Card card4 = new Card(Rank.TREFL, Suit._10);
        Card card5 = new Card(Rank.KARO, Suit._AS);
        Player pl = new Player();
        pl.playerCards.push(card1);
        pl.playerCards.push(card2);
        pl.playerCards.push(card3);
        pl.playerCards.push(card4);
        pl.playerCards.push(card5);
        assertEquals(Variants.THREE_OF_KIND, pl.HIGHestVariant(pl.playerCards));
    }







}