package algorithms;

import java.math.BigInteger;
import java.util.Random;

public class Fermat {
    private final static Random rand = new Random();

    public Fermat(){}

    public static boolean checkPrime(BigInteger num, int iteration) {

        if (num.equals(BigInteger.ONE))
            return false;

        for (int i = 0; i < iteration; i++) {
            BigInteger a = getRandomFermatBase(num);

            a = ModPow.modPow(a, num.subtract(BigInteger.ONE), num);

            if (!a.equals(BigInteger.ONE))
                return false;
        }

        return true;
    }

    private static BigInteger getRandomFermatBase(BigInteger n) {

        while (true) {
            final BigInteger a = new BigInteger(n.bitLength(), rand);
            if (BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0) {
                return a;
            }
        }
    }

}
