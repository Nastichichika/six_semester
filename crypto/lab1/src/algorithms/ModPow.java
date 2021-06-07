package algorithms;

import java.math.BigInteger;

public class ModPow {

    public ModPow(){}

    public static BigInteger modPow(BigInteger a, BigInteger n, BigInteger m) {
        if (m.compareTo(BigInteger.ZERO) <= 0)
            throw new ArithmeticException("non-positive module");
        if (n.compareTo(BigInteger.ZERO) < 0)
            return modPow(a.modInverse(m), n.negate(), m);
        if (n.equals(BigInteger.ONE))
            return a.mod(m);

        BigInteger result = BigInteger.ONE;
        BigInteger u = n;

        while (!u.equals(BigInteger.ZERO))
        {
            if (u.and(BigInteger.ONE).equals(BigInteger.ONE))
                result = result.multiply(a).mod(m);

            u = u.shiftRight(1);
            a = a.multiply(a).mod(m);
        }
        return result;
    }
}

