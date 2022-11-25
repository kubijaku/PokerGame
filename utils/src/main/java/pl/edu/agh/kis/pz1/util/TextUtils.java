package pl.edu.agh.kis.pz1.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class TextUtils {


    public static String sha512Hash(String str) {
        return DigestUtils.sha512Hex(str);
    }

    public static String getMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static String generateRandomHash() {
        return UUID.randomUUID().toString();
    }

    public static String generateHash(String str) {
        return DigestUtils.sha512Hex(str);
    }


    Vector<String> result = new Vector<>();
    static void add(Vector<String> result, Vector<String> letter)
    {
        for( String element : letter)
        {
            result.addElement(element);
        }

    }
    public static String Hello()
    {
        Vector<String> H = new Vector<String>();
        Vector<String> E = new Vector<String>();
        Vector<String> L = new Vector<String>();
        Vector<String> O = new Vector<String>();


        H.add("H   H ");
        H.add("H   H ");
        H.add("H   H ");
        H.add("HHHHH ");
        H.add("H   H ");
        H.add("H   H ");
        H.add("H   H ");

        E.add("EEEEE ");
        E.add("E     ");
        E.add("E     ");
        E.add("EEEEE ");
        E.add("E     ");
        E.add("E     ");
        E.add("EEEEE ");

        L.add("L     ");
        L.add("L     ");
        L.add("L     ");
        L.add("L     ");
        L.add("L     ");
        L.add("L     ");
        L.add("LLLLL ");

        O.add(" OOOOO  ");
        O.add("O     O ");
        O.add("O     O ");
        O.add("O     O ");
        O.add("O     O ");
        O.add("O     O ");
        O.add(" OOOOO  ");

        Vector<String> result = new Vector<String>();
        add(result, H);
        add(result, E);
        add(result, L);
        add(result, L);
        add(result, O);

        return "hi";
    }



}
