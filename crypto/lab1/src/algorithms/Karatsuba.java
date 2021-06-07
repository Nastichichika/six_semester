package algorithms;

import java.math.BigInteger;

public class Karatsuba {
    public static BigInteger multiply(BigInteger x, BigInteger y) {

        int n = Math.max(x.bitLength(), y.bitLength());
        if (n <= 200) return x.multiply(y);

        n = (n / 2) + (n % 2);

        BigInteger b = x.shiftRight(n);
        BigInteger a = x.subtract(b.shiftLeft(n));
        BigInteger d = y.shiftRight(n);
        BigInteger c = y.subtract(d.shiftLeft(n));

        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger ab_plus_cd = multiply(a.add(b), c.add(d));

        return ac.add(
                ab_plus_cd.subtract(ac).
                        subtract(bd).shiftLeft(n)).
                add(bd.shiftLeft(2*n));
    }
}
