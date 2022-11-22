package pl.edu.agh.kis.pz1.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptUtilTest
{
    @Test
    public void Addition()
    {
        assertEquals(4, CryptUtil.add(2,2));
    }

    @Test
    public void SHACoding()
    {
        String expected = "9468fed3e9a65013377e17ad96e4a48c4c78f388bf48b9339e6d7bba47dd1e6b921cff35482ac38e0b0ec5b6007626cf9250122de530af65fe91b958c92a546e";
        assertEquals(expected,CryptUtil.hashCode("ala"));
    }

}