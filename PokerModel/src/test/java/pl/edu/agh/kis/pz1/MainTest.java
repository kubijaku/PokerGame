package pl.edu.agh.kis.pz1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class MainTest
{
    @Test
    public void CreatingDeck()
    {
        Deck expected = new Deck();
        Deck result = new Deck();
        expected.factory();
        assertEquals(expected.factory(),result.factory());
    }

}