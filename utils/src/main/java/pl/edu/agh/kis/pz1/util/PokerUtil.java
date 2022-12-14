package pl.edu.agh.kis.pz1.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PokerUtil
{
    public static String hashCode(String text)
    {
        try
        {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the text string
            // returned as array of byte
            byte[] messageDigest = md.digest(text.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static double add(double num1, double num2)
    {
        return num1+num2;
    }

    public static String welcomeToPoker()
    {
        return "######################" +
                "#######WELCOME#######" +
                "#########TO##########" +
                "#######POKER#########" +
                "########GAME#########" +
                "#########!!!########";
    }
}
