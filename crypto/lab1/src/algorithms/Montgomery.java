package algorithms;

import java.math.BigInteger;
import java.util.Objects;

public class Montgomery {
    private static final BigInteger TWO = new BigInteger(String.valueOf(2));
    private static BigInteger redc(BigInteger a, BigInteger b, BigInteger n) {
        BigInteger r = TWO.shiftLeft(n.bitLength());
        BigInteger[] euclideanResult = Euclidean.gcdExtended(n, r);

        BigInteger t = a.multiply(b);
        BigInteger m = t.multiply(euclideanResult[1].negate()).and(r.subtract(BigInteger.ONE));
        BigInteger u = t.add(m.multiply(n)).shiftRight(n.bitLength());

        if (u.compareTo(n) >= 0) {
            return u.subtract(n);
        } else {
            return u;
        }
    }

    public static BigInteger multiply(BigInteger a, BigInteger b, BigInteger mod) {
        if (!mod.testBit(0) || mod.compareTo(BigInteger.ONE) <= 0) {
            throw new IllegalArgumentException("Non-positive module or not odd");
        }
        BigInteger un = redc(
                a.shiftLeft(mod.bitLength()).mod(mod),
                b.shiftLeft(mod.bitLength()).mod(mod),
                mod
        );
        return redc(un, BigInteger.ONE, mod);
    }

    public static BigInteger pow(BigInteger a, BigInteger e, BigInteger n) {
        if (!n.testBit(0) || n.compareTo(BigInteger.ONE) <= 0) {
            throw new IllegalArgumentException("Non-positive module or not odd");
        }
        int r = n.bitLength();
        BigInteger an = a.shiftLeft(r).mod(n);
        BigInteger xn = BigInteger.ONE.shiftLeft(r).mod(n);
        for (int i = e.bitLength() - 1; i >= 0; i--) {
            xn = redc(xn, xn, n);
            if (e.testBit(i)) {
                xn = redc(xn, an, n);
            }
        }

        return redc(xn, BigInteger.ONE, n);
    }

}
