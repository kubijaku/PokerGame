package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.CryptUtil;

import java.util.*;


/**
 * Main class - nothing interesting here
 * @author Me
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world!");
        Deck deck1 = new Deck();
        deck1.fulfillDeck();
        deck1.factory();
        deck1.print();
        deck1.shuffle();
        ArrayList<Player> allPlayers = new ArrayList<Player>();
        for (int i=0;i<5;i++)
        {
            Player newPlayer = new Player();
            deck1.give5Cards(newPlayer);
            allPlayers.add(newPlayer);
        }
        for (Player player : allPlayers)
        {
            System.out.println("###Player###");
            player.showCards();
            System.out.println();
        }
        deck1.print();
        Card card1 = new Card(Rank.KARO,Suit._2);
        Card card2 = new Card(Rank.KARO,Suit._3);
        Card card3 = card1;
        HashSet<Card> cardsSet = new HashSet<Card>();
        cardsSet.add(card1);        cardsSet.add(card2);        cardsSet.add(card3);
        for ( Card card : cardsSet)
        {
            card.print();
        }

        System.out.println(CryptUtil.hashCode("ala"));


    }
}
enum Rank
{
    PIK,
    KARO,
    TREFL,
    KIER
}
enum Suit
{
    _2,
    _3,
    _4,
    _5,
    _6,
    _7,
    _8,
    _9,
    _10,
    _WALET,
    _DAMA,
    _KROL,
    _AS
}

