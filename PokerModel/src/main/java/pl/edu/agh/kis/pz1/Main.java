package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.PokerUtil;

import java.util.*;


/**
 * Main class - do nothing
 * @author Me
 */
public class Main {

    public static void main(String[] args)
    {
        System.out.println( PokerUtil.hashCode("hi"));


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

